package event_horizon.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "place", nullable = false)
    private String place;

    @Type(PointType.class)
    @Column(name = "coordinates", nullable = false)
    private Point point;

    @Column(name = "event_time", nullable = false)
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id", nullable = false)
    private Customer creator;
}
