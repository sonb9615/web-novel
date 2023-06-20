package numble.webnovel.novel.dto;

import lombok.Getter;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.enums.Genre;
import numble.webnovel.novel.enums.SerialStatus;

import java.time.LocalDateTime;

@Getter
public class NovelRegisterRequest {

    private String title;
    private String author;
    private String novelInfo;
    private String novelImg;
    private int episodeCost;
    private String genre;
    private String serialStatus;

    public Novel toNovel(){

        Genre genreEnum = Genre.getGenreCode(genre);
        SerialStatus statusEnum = SerialStatus.getNovelStatus(serialStatus);

        return Novel.builder()
                .title(title)
                .author(author)
                .novelInfo(novelInfo)
                .novelImg(novelImg)
                .episodeCost(episodeCost)
                .genre(genreEnum)
                .serialStatus(statusEnum)
                .regDt(LocalDateTime.now())
                .build();
    }

    //ForTest
    public void setTitleForTest(String title){
        this.title = title;
    }

    public void setAuthorForTest(String author){
        this.author = author;
    }

    public void setNovelInfoForTest(String novelInfo){
        this.novelInfo = novelInfo;
    }

    public void setNovelImgForTest(String novelImg){
        this.novelImg = novelImg;
    }

    public void setEpisodeCostForTest(int cost){
        this.episodeCost = cost;
    }

    public void setGenreNameForTest(String genre){
        this.genre = genre;
    }

    public void setSerialStatusForTest(String serialStatus){
        this.serialStatus = serialStatus;
    }
}
