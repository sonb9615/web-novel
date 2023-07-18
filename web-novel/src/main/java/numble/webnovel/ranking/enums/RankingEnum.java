package numble.webnovel.ranking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RankingEnum {

    NOVEL_PAYMENT_RANKING("PAYMENT_RANKING")
    ,FAVORITE_NOVEL_RANKING("FAVORITE_NOVEL_RANKING")
    ;

    private final String value;


}
