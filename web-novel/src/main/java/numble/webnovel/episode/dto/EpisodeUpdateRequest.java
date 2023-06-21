package numble.webnovel.episode.dto;

import lombok.Getter;

@Getter
public class EpisodeUpdateRequest {

    private String content;
    private boolean freeYn;
    private int totalPage;
    private int capacity;

    //For Test

    public void setContentForTest(String content) {
        this.content = content;
    }

    public void setFreeYnForTest(boolean freeYn) {
        this.freeYn = freeYn;
    }

    public void setTotalPageForTest(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCapacityForTest(int capacity) {
        this.capacity = capacity;
    }
}
