package numble.webnovel.favoriteNovel.dto;

import lombok.Builder;
import lombok.Getter;
import numble.webnovel.favoriteNovel.domain.FavoriteNovel;
import numble.webnovel.novel.enums.SerialStatus;

@Getter
public class FavoriteNovelInfoResponse {

    private Long favoriteNovelId;
    private Long novelId;
    private String title;
    private String novelImg;
    private String author;
    private String serialStatus;

    @Builder
    public FavoriteNovelInfoResponse(Long favoriteNovelId, Long novelId, String title, String novelImg, String author, String serialStatus) {
        this.favoriteNovelId = favoriteNovelId;
        this.novelId = novelId;
        this.title = title;
        this.novelImg = novelImg;
        this.author = author;
        this.serialStatus = serialStatus;
    }

    public static FavoriteNovelInfoResponse toFavoriteNovelInfoResponse(FavoriteNovel favoriteNovel){

        String serialStatusName = SerialStatus.toStatusName(favoriteNovel.getNovel().getSerialStatus());

        return FavoriteNovelInfoResponse.builder()
                .favoriteNovelId(favoriteNovel.getFavoriteNovelId())
                .novelId(favoriteNovel.getNovel().getNovelId())
                .title(favoriteNovel.getNovel().getTitle())
                .novelImg(favoriteNovel.getNovel().getNovelImg())
                .author(favoriteNovel.getNovel().getAuthor())
                .serialStatus(serialStatusName)
                .build();
    }
}
