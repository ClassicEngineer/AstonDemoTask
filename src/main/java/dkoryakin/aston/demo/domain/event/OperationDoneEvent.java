package dkoryakin.aston.demo.domain.event;

import dkoryakin.aston.demo.domain.OperationType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Getter
@ToString
public class OperationDoneEvent {

    private Long accountId;

    private Long receivingAccountId;

    private OperationType type;

    private BigDecimal sum;

    @Builder.Default
    private Date date = new Date();

}
