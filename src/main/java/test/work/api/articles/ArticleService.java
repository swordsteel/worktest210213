package test.work.api.articles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {

    Page<ArticleResponse> listArticles(Pageable pageable);

    ArticleResponse createArticles(ArticleRequest request);

    List<ArticlesStatisticResponse> listStatisticWeek();

}
