package numble.webnovel.repository.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovelTicketChargeRequest {

    private String userNo;
    private String novelId;
    private int chargeTicketsCnt;
    private int episodeCost;
    private int userCache;

    public static NovelTicketChargeRequest novelTicketChargeInfo(String userNo, String novelId, int chargeTicketsCnt) {
        NovelTicketChargeRequest novelTicketChargeRequest = new NovelTicketChargeRequest();
        novelTicketChargeRequest.setUserNo(userNo);
        novelTicketChargeRequest.setNovelId(novelId);
        novelTicketChargeRequest.setChargeTicketsCnt(chargeTicketsCnt);
        return novelTicketChargeRequest;
    }
}
