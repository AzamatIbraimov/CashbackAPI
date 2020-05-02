package kg.nurtelecom.cashbackapi.entity;

import kg.nurtelecom.cashbackapi.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Client Balance History
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "balance_history")
public class BalanceHistory {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "balance_history_seq", sequenceName = "balance_history_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "balance_history_seq")
    private Long id;

    /**
     * Balance ID
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "balance_id")
    private Balance balance;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "users_id")
    private User user;
    /**
     * Operation Type
     * CREDIT, DEBIT;
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType operationType;

    /**
     * Amount
     */
    @Column(name = "amount")
    private Double amount;

    /**
     * Invoice amount
     */
    @Column(name = "invoice_amount")
    private Double invoice_amount;

    /**
     * Check
     */
    @Column(name = "bill")
    private String bill;

    /**
     * Created date
     */
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;
}
