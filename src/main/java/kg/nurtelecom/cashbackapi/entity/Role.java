package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    private Long id;

    /**
     * Users
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /**
     * Role name
     */
    @Column(name = "name")
    private String name;

    /**
     * Role description
     */
    @Column(name = "description")
    private String description;

}
