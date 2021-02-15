package test.work.api.articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT FORMATDATETIME(a.published_at, 'yyyy-MM-dd') AS date , COUNT(a.id) AS articles\n" +
                    "FROM articles a\n" +
                    "WHERE FORMATDATETIME(a.published_at, 'yyyy-MM-dd') > DATEADD('WEEK', -1, current_date)\n" +
                    "GROUP BY date")
    List<ArticleStatistic> makeStatisticWeek();

}
