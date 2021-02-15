package test.work.api.articles;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
public class ArticleResponse {

    private UUID uuid;

    private ZonedDateTime publishedAt;

    private String title;

    private String author;

    private String content;

}
