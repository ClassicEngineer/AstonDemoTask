package dkoryakin.aston.demo.api;

import dkoryakin.aston.demo.api.body.request.AccountCreatePostBody;
import dkoryakin.aston.demo.api.body.request.AccountDepositPostBody;
import dkoryakin.aston.demo.api.body.response.AccountView;
import dkoryakin.aston.demo.domain.Account;
import dkoryakin.aston.demo.domain.Pin;
import dkoryakin.aston.demo.domain.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountRestControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountRepository accountRepository;

    private static final String ACCOUNT_API_PREFIX = "api/v1/account/";

    private static final String ALL = "all";

    private static final String DEPOSIT  = "/deposit";

    private static final String CREATE  = "create";
    @Test
    public void shouldReturnCorrectLabel_whenMakingRequestToGetAccounts()  {
        var response = this.restTemplate.getForObject("http://localhost:" + port + "/" + ACCOUNT_API_PREFIX + ALL, String.class);
        Assertions.assertTrue(response.contains("accounts"));
    }


    @Test
    public void shouldReturnCorrectBody_WhenMakingDepositOperation() {
        Long accountId = 1L;
        Double amount = 100.0;
        Account test = Account.builder()
                .id(accountId)
                .balance(0.0)
                .pin(Pin.valueOf("1234"))
                .name("Dan")
                .build();
        accountRepository.save(test);

        AccountView response = this.restTemplate.postForObject("http://localhost:" + port + "/" + ACCOUNT_API_PREFIX + accountId + DEPOSIT,
                new AccountDepositPostBody(amount), AccountView.class);

        Assertions.assertEquals(100.0, response.getBalance());
        Assertions.assertEquals(accountId, response.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "333", "10000", "abcd"})
    public void shouldReturnError_whenCreatingAccountWithIncorrectPin(String value) {
        var response = this.restTemplate.postForObject("http://localhost:" + port + "/" + ACCOUNT_API_PREFIX + CREATE,
                new AccountCreatePostBody("Dan", value), String.class);
        Assertions.assertTrue(response.contains("Invalid pin code"));
    }
}
