package numble.webnovel.episode.dto;

import lombok.Getter;
import numble.webnovel.episode.domain.Episode;
import numble.webnovel.novel.domain.Novel;

import java.time.LocalDateTime;

@Getter
public class EpisodeRegisterRequest {

    private int episodeNo;
    private String episodeTitle;
    private String content;
    private int totalPage;
    private boolean freeYn;
    private int capacity;

    public Episode toEpisode(Novel novel){

        return Episode.builder()
                .episodeNo(episodeNo)
                .episodeTitle(episodeTitle)
                .content(content)
                .totalPage(totalPage)
                .freeYn(freeYn)
                .capacity(capacity)
                .novel(novel)
                .regDt(LocalDateTime.now())
                .build();
    }

    //For Test
    public void setEpisodeNoForTest(int episodeNo){
        this.episodeNo = episodeNo;
    }

    public void setEpisodeTitleForTest(String episodeTitle){
        this.episodeTitle = episodeTitle;
    }

    public void setContentForTest(String content){
        this.content = content;
    }

    public void setTotalPageForTest(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCapacityForTest(int capacity) {
        this.capacity = capacity;
    }
}
