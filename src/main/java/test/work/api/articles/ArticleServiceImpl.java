package test.work.api.articles;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Page<ArticleResponse> listArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return new PageImpl<>(
                articles.stream()
                        .map(this::mapArticleResponse)
                        .collect(Collectors.toList()),
                articles.getPageable(),
                articles.getTotalPages());
    }

    @Override
    public ArticleResponse createArticles(ArticleRequest request) {
        return mapArticleResponse(articleRepository.save(Article.builder()
                .uuid(UUID.randomUUID())
                .publishedAt(request.getPublishAt())
                .author(request.getAuthor())
                .title(request.getTitle())
                .content(request.getContent())
                .build()));
    }

    @Override
    public List<ArticlesStatisticResponse> listStatisticWeek() {
        return articleRepository.makeStatisticWeek().stream()
                .map(this::mapArticleStatisticResponse)
                .collect(Collectors.toList());
    }

    private ArticleResponse mapArticleResponse(Article article) {
        return ArticleResponse.builder()
                .uuid(article.getUuid())
                .publishedAt(article.getPublishedAt())
                .author(article.getAuthor())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }

    private ArticlesStatisticResponse mapArticleStatisticResponse(ArticleStatistic statistic) {
        return ArticlesStatisticResponse.builder()
                .date(statistic.getDate())
                .articles(statistic.getArticles())
                .build();
    }

}
