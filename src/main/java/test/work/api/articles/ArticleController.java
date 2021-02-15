package test.work.api.articles;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public ResponseEntity<?> getArticles(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(articleService.listArticles(pageable));
    }

    @PostMapping("/articles")
    public ResponseEntity<?> getArticles(@Valid @RequestBody ArticleRequest request) {
        return new ResponseEntity<>(articleService.createArticles(request), HttpStatus.CREATED);
    }

    @GetMapping("/statistic/articles-week")
    public ResponseEntity<?> getWeekStatistic() {
        return ResponseEntity.ok(articleService.listStatisticWeek());
    }

}
