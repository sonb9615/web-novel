package numble.webnovel.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheCargeInfo {

    private String userNo;
    private String novelId;
    private String episodeId;
    private int payTicketsCnt;
    private int money;

    public static CacheCargeInfo cacheCargeInfo(String userNno, String episodeId, int money) {
        CacheCargeInfo cacheCargeInfo = new CacheCargeInfo();
        cacheCargeInfo.setUserNo(userNno);
        cacheCargeInfo.setEpisodeId(episodeId);
        cacheCargeInfo.setMoney(money);
        return cacheCargeInfo;
    }
}
