package dkoryakin.aston.demo.domain.event;

import dkoryakin.aston.demo.domain.OperationType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Builder
@Getter
@ToString
public class OperationDoneEvent {

    private Long accountInId;

    private Long accountOutId;

    private OperationType type;

    private Double sum;

    @Builder.Default
    private Date date = new Date();

}
