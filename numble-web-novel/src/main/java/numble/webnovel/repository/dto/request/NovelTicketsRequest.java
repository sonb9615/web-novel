package numble.webnovel.repository.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NovelTicketsRequest {

    private Long memberId;
    private Long novelId;
    private Long episodeId;

}
