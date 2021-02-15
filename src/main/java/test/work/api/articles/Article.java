package test.work.api.articles;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "articles")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Article {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "published_at")
    private ZonedDateTime publishedAt;

    @Column(name = "uuid", updatable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID uuid;

    @Column(name = "title")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String title;

    @Column(name = "author")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String author;

    @Column(name = "content")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String content;

}
