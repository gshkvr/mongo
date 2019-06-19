package by.epam.kvirykashvili.javalabtask.domain.model;

import lombok.*;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "tours")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@SqlResultSetMapping(name = "deleteCountryResult")
@NamedQuery(
        name = "readAllCountriesNamed",
        query = "From Country"
)
@NamedNativeQuery(
        name = "deleteCountryNative",
        query = "delete from public.country where id = ?",
        resultSetMapping = "deleteCountryResult"
)
public class Country implements Serializable {

    private static final long serialVersionUID = -2848428274780656759L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Tour> tours;

    @Version
    private int version;
}