package kg.nurtelecom.cashbackapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Event
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "event_seq", sequenceName = "event_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    private Long id;

    /**
     * Organization which create Event
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Event image encode to base64
     */
    @Column(name = "image")
    private String image;

    /**
     * Event name
     */
    @Column(name = "name")
    private String name;

    /**
     * Date when Event starts
     */
    @Column(name = "date_from")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dateFrom;

    /**
     * Date when Event finishes
     */
    @Column(name = "date_to")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dateTo;

    /**
     * Event description
     */
    @Column(name = "description")
    private String description;

    /**
     * Event created date
     */
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;
}
