package numble.webnovel.chargeCache.dto;

import lombok.Getter;
import numble.webnovel.chargeCache.domain.ChargeCacheHis;
import numble.webnovel.member.domain.Member;

import java.time.LocalDateTime;

@Getter
public class ChargeCacheRequest {

    private int cache;
    private int cost;

    public ChargeCacheHis toChargeCacheHis(Member member){
        return ChargeCacheHis.builder()
                .cache(this.cache)
                .cost(this.cost)
                .regDt(LocalDateTime.now())
                .member(member)
                .build();
    }

    //For Test
    public void setCacheForTest(int cache) {
        this.cache = cache;
    }

    public void setCostForTest(int cost) {
        this.cost = cost;
    }
}
