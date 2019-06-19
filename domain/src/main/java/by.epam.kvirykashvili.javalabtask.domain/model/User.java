package by.epam.kvirykashvili.javalabtask.domain.model;

import lombok.*;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@SqlResultSetMapping(name = "deleteUserResult")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@NamedQuery(
        name = "readAllUsersNamed",
        query = "From User"
)
@NamedNativeQuery(
        name = "deleteUserNative",
        query = "delete from public.users where id = ?",
        resultSetMapping = "deleteUserResult"
)
@TypeDef(
        name = "userrole",
        typeClass = PostgreUserRole.class
)
@ToString(exclude = {"reviews", "tours"})
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1136031476741894907L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @Column(name = "user_role")
    @Type(type = "userrole")
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.MEMBER;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usertour",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "tour_id")}
    )
    private List<Tour> tours;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userRole.toString()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Version
    private int version;
}