package by.epam.kvirykashvili.javalabtask.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SqlResultSetMapping(name = "deleteReviewResult")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@NamedQuery(
        name = "readAllReviewsNamed",
        query = "From Review"
)
@NamedNativeQuery(
        name = "deleteReviewNative",
        query = "delete from public.review where id = ?",
        resultSetMapping = "deleteReviewResult"
)
public class Review implements Serializable {

    private static final long serialVersionUID = 1277929585720264864L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Date date;

    @NotNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Version
    private int version;
}