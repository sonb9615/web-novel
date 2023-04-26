package numble.webnovel.repository.dto.response;

import lombok.Data;

@Data
public class NovelTicketsResponse {

    private String novelId;
    private String episodeId;

    public static NovelTicketsResponse createNovelTicketsResponse(String novelId, String episodeId) {
        NovelTicketsResponse novelTicketsResponse = new NovelTicketsResponse();
        novelTicketsResponse.setNovelId(novelId);
        novelTicketsResponse.setEpisodeId(episodeId);
        return novelTicketsResponse;
    }
}
