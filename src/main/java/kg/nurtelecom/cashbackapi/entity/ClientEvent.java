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
 * Events that was saved by Client,
 * with ability to set notification date.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client_event")
public class ClientEvent {

    /**
     * Identification
     */
    @Id
    @SequenceGenerator(name = "client_event_seq", sequenceName = "client_event_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_event_seq")
    private Long id;

    /**
     * Client
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * Event
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    /**
     * Created Date
     */
    @CreationTimestamp
    @Column(name = "created_date")
    @DateTimeFormat(pattern="dd.MM.yyyy")
    private Date createdDate;

    /**
     * Notification date
     */
    @Column(name = "notification_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date notificationDate;


}
