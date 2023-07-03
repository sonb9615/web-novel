package numble.webnovel.novel.dto;

import lombok.Builder;
import lombok.Getter;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.enums.Genre;
import numble.webnovel.novel.enums.SerialStatus;

@Getter
public class NovelInfoResponse {

    private Long novelId;
    private String title;
    private String author;
    private String novelImg;
    private String serialStatus;
    private String genre;

    @Builder
    public NovelInfoResponse(Long novelId, String title, String author, String novelImg, String serialStatus, String genre) {
        this.novelId = novelId;
        this.title = title;
        this.author = author;
        this.novelImg = novelImg;
        this.serialStatus = serialStatus;
        this.genre = genre;
    }

    public static NovelInfoResponse toNovelInfoResponse(Novel novel){

        String serialStatusName = SerialStatus.toStatusName(novel.getSerialStatus());
        String genreName = Genre.toGenreName(novel.getGenre());

        return NovelInfoResponse.builder()
                .novelId(novel.getNovelId())
                .title(novel.getTitle())
                .author(novel.getAuthor())
                .novelImg(novel.getNovelImg())
                .serialStatus(serialStatusName)
                .genre(genreName)
                .build();
    }
}
