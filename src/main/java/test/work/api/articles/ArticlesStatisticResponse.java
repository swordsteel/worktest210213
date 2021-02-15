package test.work.api.articles;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ArticlesStatisticResponse {

    private LocalDate date;

    private int articles;

}
