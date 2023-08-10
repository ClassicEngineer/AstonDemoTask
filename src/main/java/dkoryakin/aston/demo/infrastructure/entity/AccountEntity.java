package dkoryakin.aston.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "balance", columnDefinition = "decimal DEFAULT 0.0")
    @ColumnDefault("0.0")
    @Builder.Default
    private Double balance = 0.0;

}
