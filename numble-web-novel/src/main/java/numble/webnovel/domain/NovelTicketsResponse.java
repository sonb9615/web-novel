package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovelTicketsResponse {

    private String novelId;
    private String episodeId;

    public static NovelTicketsResponse novelTicketsResponse(String novelId, String episodeId) {
        NovelTicketsResponse novelTicketsResponse = new NovelTicketsResponse();
        novelTicketsResponse.setNovelId(novelId);
        novelTicketsResponse.setEpisodeId(episodeId);
        return novelTicketsResponse;
    }
}
