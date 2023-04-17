package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NovelTicketsRequest {

    private String userNo;
    private String novelId;
    private String episodeId;

}
