package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovelTicketChargeInfo {

    private String userNo;
    private String novelId;
    private int chargeTicketsCnt;
    private int episodeCost;
    private int userCache;

    public static  NovelTicketChargeInfo novelTicketChargeInfo(String userNo, String novelId, int chargeTicketsCnt) {
        NovelTicketChargeInfo novelTicketChargeInfo = new NovelTicketChargeInfo();
        novelTicketChargeInfo.setUserNo(userNo);
        novelTicketChargeInfo.setNovelId(novelId);
        novelTicketChargeInfo.setChargeTicketsCnt(chargeTicketsCnt);
        return novelTicketChargeInfo;
    }
}
