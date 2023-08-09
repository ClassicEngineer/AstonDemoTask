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

    public Long accountInId;

    public Long accountOutId;

    public OperationType type;

    public Double sum;

    @Builder.Default
    public Date date = new Date();

}
