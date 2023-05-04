package numble.webnovel.repository.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovelTicketChargeRequest {

    private Long memberId;
    private Long novelId;
    private int chargeTicketsCnt;
    private int episodeCost;
    private int userCache;

    public static NovelTicketChargeRequest novelTicketChargeInfo(Long memberId, Long novelId, int chargeTicketsCnt) {
        NovelTicketChargeRequest novelTicketChargeRequest = new NovelTicketChargeRequest();
        novelTicketChargeRequest.setMemberId(memberId);
        novelTicketChargeRequest.setNovelId(novelId);
        novelTicketChargeRequest.setChargeTicketsCnt(chargeTicketsCnt);
        return novelTicketChargeRequest;
    }
}
