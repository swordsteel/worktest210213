package test.work.api.users;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "username")
@EqualsAndHashCode(exclude = "authorities")
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = PERSIST)
    private Set<UserAuthority> authorities = new HashSet<>();

}
