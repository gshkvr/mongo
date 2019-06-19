package by.epam.kvirykashvili.javalabtask.domain.model;

import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@TypeDef(
        name = "tourtype",
        typeClass = PostgreTourType.class
)
@SqlResultSetMapping(name = "deleteTourResult")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@NamedQuery(
        name = "readAllToursNamed",
        query = "From Tour"
)
@NamedNativeQuery(
        name = "deleteTourNative",
        query = "delete from public.tour where id = ?",
        resultSetMapping = "deleteTourResult"
)
@ToString(exclude = {"reviews", "users"})
public class Tour implements Serializable {

    private static final long serialVersionUID = 2315670959539664302L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String photo;

    @NotNull
    private Date date;

    @NotNull
    private int duration;

    @NotNull
    private String description;

    @NotNull
    private int cost;

    @Column(name = "tour_type")
    @Type(type = "tourtype")
    @Enumerated(EnumType.STRING)
    private TourType tourType;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(mappedBy = "tours")
    private List<User> users;

    @OneToMany(mappedBy = "tour")
    private List<Review> reviews;

    @Version
    private int version;
}