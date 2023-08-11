package dkoryakin.aston.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "beneficiary_name")
    private String name;

    @Column(name = "pin")
    private String pin;

    @Column(name = "balance")
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

}
