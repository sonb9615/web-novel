package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheChargeRequest {

    private String userNo;
    private String novelId;
    private String episodeId;
    private int payTicketsCnt;
    private int money;

    public static CacheChargeRequest cacheChargeInfo(String userNno, String episodeId, int money) {
        CacheChargeRequest cacheChargeRequest = new CacheChargeRequest();
        cacheChargeRequest.setUserNo(userNno);
        cacheChargeRequest.setEpisodeId(episodeId);
        cacheChargeRequest.setMoney(money);
        return cacheChargeRequest;
    }
}
