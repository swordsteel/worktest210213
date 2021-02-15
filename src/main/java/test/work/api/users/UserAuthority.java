package test.work.api.users;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(of = "authority")
@Table(name = "authorities")
public class UserAuthority implements Serializable {

    @Id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "authority")
    private String authority;

}
