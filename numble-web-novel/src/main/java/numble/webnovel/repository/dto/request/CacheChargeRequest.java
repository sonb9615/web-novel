package numble.webnovel.repository.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheChargeRequest {

    private Long memberId;
    private Long novelId;
    private Long episodeId;
    private int payTicketsCnt;
    private int money;

    public static CacheChargeRequest cacheChargeInfo(Long memberId, Long episodeId, int money) {
        CacheChargeRequest cacheChargeRequest = new CacheChargeRequest();
        cacheChargeRequest.setMemberId(memberId);
        cacheChargeRequest.setEpisodeId(episodeId);
        cacheChargeRequest.setMoney(money);
        return cacheChargeRequest;
    }
}
