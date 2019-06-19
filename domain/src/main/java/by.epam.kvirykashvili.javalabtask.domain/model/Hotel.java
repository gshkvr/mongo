package by.epam.kvirykashvili.javalabtask.domain.model;

import lombok.*;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "tours")
@TypeDef(
        name = "features",
        typeClass = PostgreFeatures.class
)
@OptimisticLocking(type = OptimisticLockType.VERSION)
@SqlResultSetMapping(name = "deleteHotelResult")
@NamedQuery(
        name = "readAllHotelsNamed",
        query = "From Hotel"
)
@NamedNativeQuery(
        name = "deleteHotelNative",
        query = "delete from public.hotel where id = ?",
        resultSetMapping = "deleteHotelResult"
)
public class Hotel implements Serializable {

    private static final long serialVersionUID = -3448788786752231291L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @Min(1)
    @Max(5)
    @NotNull
    private int stars;

    @NotNull
    private String website;

    @NotNull
    private String latitude;

    @NotNull
    private String longitude;

    @NotNull
    @Type(type = "features")
    @Column(name = "features", columnDefinition = "features[]")
    private Features[] features;

    @OneToMany(mappedBy = "hotel")
    private List<Tour> tours;

    @Version
    private int version;
}