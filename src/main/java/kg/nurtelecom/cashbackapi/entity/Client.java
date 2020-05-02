package kg.nurtelecom.cashbackapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kg.nurtelecom.cashbackapi.enums.ClientSex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Clients
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Long id;

    /**
     * The list of Clients' Organizations
     */
    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "client_organization",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id")
    )
    private Set<Organization> organizations;

    /**
     * Personal code for operations
     */
    @Column(name = "personal_code")
    private String personalCode;

    /**
     * Last name
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * First name
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Client image encode to base64
     */
    @Column(name = "image")
    private String image;

    /**
     * Patronymic
     */
    @Column(name = "patronymic")
    private String patronymic;

    /**
     * Gender
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private ClientSex clientSex;

    /**
     * Language
     */
    @Column(name = "locale")
    private String locale;

    /**
     * Nationality
     */
    @Column(name = "nationality")
    private String nationality;

    /**
     * Created date
     */
    @CreationTimestamp
    @Column(name = "created_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date createdDate;
}
