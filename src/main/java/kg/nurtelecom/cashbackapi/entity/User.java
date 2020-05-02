package kg.nurtelecom.cashbackapi.entity;

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
 * Users of whole system
 * Super Admin
 * Organization Admin
 * Managers
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    /**
     * User Role
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "role_id", nullable = false)
//    private Role role;

    /**
     * User Organization
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Username
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * Password
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * User status
     * ACTIVE / NOT_ACTIVE / DELETED
     */
    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean status;

    /**
     * Created Date
     */
    @CreationTimestamp
    @Column(name = "create_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date createdDate;

    @Column(name = "rolenameshort")
    private String roleNameShort;
}
