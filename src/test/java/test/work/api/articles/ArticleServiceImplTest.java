package test.work.api.articles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {

    @Mock
    private ArticleRepository repository;

    @InjectMocks
    private ArticleServiceImpl service;

    @Test
    public void listArticles() {
        // given
        var article = Article.builder()
                .id(1)
                .publishedAt(ZonedDateTime.of(2021, 2, 15, 15, 2, 1, 0, ZoneId.of("UTC")))
                .author("user")
                .title("title")
                .content("content")
                .uuid(UUID.fromString("01234567-89ab-cdef-0123-456789abcdef"))
                .build();
        Pageable pageable = mock(Pageable.class);
        var page = new PageImpl(List.of(article), pageable, 1);
        when(repository.findAll(any(Pageable.class))).thenReturn(page);

        // when
        var response = service.listArticles(pageable);

        // then
        verify(repository, times(1)).findAll(pageable);
        assertEquals(response.getContent().size(), 1);
        assertEquals(response.getContent().get(0).getUuid(), article.getUuid());
        assertEquals(response.getContent().get(0).getPublishedAt(), article.getPublishedAt());
        assertEquals(response.getContent().get(0).getAuthor(), article.getAuthor());
        assertEquals(response.getContent().get(0).getTitle(), article.getTitle());
        assertEquals(response.getContent().get(0).getContent(), article.getContent());
        assertEquals(response.getTotalElements(), 1);
    }

    @Test
    public void createArticles() {
        // given
        var articleRequest = ArticleRequest.builder()
                .publishAt(ZonedDateTime.of(2021, 2, 15, 15, 2, 1, 0, ZoneId.of("UTC")))
                .author("user")
                .title("title")
                .content("content")
                .build();
        when(repository.save(any())).then(invocationOnMock -> invocationOnMock.getArgument(0));

        // when
        var response = service.createArticles(articleRequest);

        // then
        verify(repository, times(1)).save(any());
        assertEquals(response.getPublishedAt(), articleRequest.getPublishAt());
        assertEquals(response.getAuthor(), articleRequest.getAuthor());
        assertEquals(response.getTitle(), articleRequest.getTitle());
        assertEquals(response.getContent(), articleRequest.getContent());
    }

    @Test
    public void listStatisticWeek() {
        // given
        var articleStatistic = new ArticleStatistic() {

            @Override
            public LocalDate getDate() {
                return LocalDate.of(2021, 2, 15);
            }

            @Override
            public int getArticles() {
                return 5;
            }

        };
        when(repository.makeStatisticWeek()).thenReturn(List.of(articleStatistic));

        // when
        var response = service.listStatisticWeek();

        // then
        verify(repository, times(1)).makeStatisticWeek();
        assertEquals(response.size(), 1);
        assertEquals(response.get(0).getDate(), articleStatistic.getDate());
        assertEquals(response.get(0).getArticles(), articleStatistic.getArticles());
    }

}
