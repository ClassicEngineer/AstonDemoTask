package dkoryakin.aston.demo.api.body.response;

import dkoryakin.aston.demo.domain.OperationType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class HistoryEntryView {

    private OperationType type;

    private Date date;

    private Long incomeAccountId;

    private Long receivingAccountId;

    private BigDecimal sum;
}
