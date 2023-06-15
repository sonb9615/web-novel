package numble.webnovel.novel.dto;

import lombok.Getter;
import numble.webnovel.novel.domain.Novel;
import numble.webnovel.novel.enums.Genre;
import numble.webnovel.novel.enums.NovelStatus;

import java.time.LocalDateTime;

@Getter
public class NovelRegisterRequest {

    private String title;
    private String author;
    private String novelInfo;
    private String novelImg;
    private int episodeCost;
    private String genre;
    private String status;

    public Novel toNovel(){

        Genre genreEnum = Genre.getGenreCode(genre);
        NovelStatus novelStatus = NovelStatus.getNovelStatus(status);

        return Novel.builder()
                .title(title)
                .author(author)
                .novelInfo(novelInfo)
                .novelImg(novelImg)
                .episodeCost(episodeCost)
                .genre(genreEnum)
                .novelStatus(novelStatus)
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

    public void setStatus(String status){
        this.status = status;
    }
}
