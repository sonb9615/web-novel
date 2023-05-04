package numble.webnovel.repository.dto.response;

import lombok.Data;

@Data
public class NovelTicketsResponse {

    private Long novelId;
    private Long episodeId;

    public static NovelTicketsResponse createNovelTicketsResponse(Long novelId, Long episodeId) {
        NovelTicketsResponse novelTicketsResponse = new NovelTicketsResponse();
        novelTicketsResponse.setNovelId(novelId);
        novelTicketsResponse.setEpisodeId(episodeId);
        return novelTicketsResponse;
    }
}
