package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheCargeInfo {

    private String user_no;
    private String novel_id;
    private String episode_id;
    private int payTicketsCnt;
    private int money;

    public static CacheCargeInfo cacheCargeInfo(String user_no, String episode_id, int money) {
        CacheCargeInfo cacheCargeInfo = new CacheCargeInfo();
        cacheCargeInfo.setUser_no(user_no);
        cacheCargeInfo.setEpisode_id(episode_id);
        cacheCargeInfo.setMoney(money);
        return cacheCargeInfo;
    }
}
