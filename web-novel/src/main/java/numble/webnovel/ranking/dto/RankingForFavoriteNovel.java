package numble.webnovel.ranking.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RankingForFavoriteNovel {

    private Long novelId;
    private String title;
    private String novelImg;
    private Long count;
    private String serialStatus;
    private String genre;

    @Builder
    public RankingForFavoriteNovel(Long novelId, String title, String novelImg, Long count, String serialStatus, String genre) {
        this.novelId = novelId;
        this.title = title;
        this.novelImg = novelImg;
        this.count = count;
        this.serialStatus = serialStatus;
        this.genre = genre;
    }

}
