package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheCargeRequest {

    private String userNo;
    private String novelId;
    private String episodeId;
    private int payTicketsCnt;
    private int money;

    public static CacheCargeRequest cacheCargeInfo(String userNno, String episodeId, int money) {
        CacheCargeRequest cacheCargeRequest = new CacheCargeRequest();
        cacheCargeRequest.setUserNo(userNno);
        cacheCargeRequest.setEpisodeId(episodeId);
        cacheCargeRequest.setMoney(money);
        return cacheCargeRequest;
    }
}
