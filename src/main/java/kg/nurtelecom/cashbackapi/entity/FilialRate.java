package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Filial Rate
 * Evaluated by Clients
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filial_rate")
public class FilialRate {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "filial_rate_seq", sequenceName = "filial_rate_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filial_rate_seq")
    private Long id;

    /**
     * Client
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * Organization
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Filial
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "filial_id")
    private Filial filial;

    /**
     * Rate
     */
    @Column(name = "rate")
    private Double rate;

    /**
     * Comment
     */
    @Column(name = "comment")
    private String comment;

    /**
     * Created date
     */
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;
}
