package kg.nurtelecom.cashbackapi.entity;

import kg.nurtelecom.cashbackapi.enums.BonusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "org_bonus_type")
public class OrgBonusType {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "org_bonus_type_seq", sequenceName = "org_bonus_type_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_bonus_type_seq")
    private Long id;

    /**
     * Name
     */
    @Column
    private String name;

    /**
     * Value Type
     * VALUE / LIST_TYPE
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "value_type")
    private BonusType valueType;

    /**
     * Bonus description
     */
    @Column
    private String description;
}
