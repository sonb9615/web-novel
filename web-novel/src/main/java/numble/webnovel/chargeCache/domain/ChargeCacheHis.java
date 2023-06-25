package numble.webnovel.chargeCache.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "charge_cache_his")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChargeCacheHis {

    @Id
    @Column(name = "charge_his_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String chargeHisId;

    private LocalDateTime regDt;
    private int cost;
    private int cache;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public ChargeCacheHis(String chargeHisId, LocalDateTime regDt, int cost, int cache, Member member) {
        this.chargeHisId = chargeHisId;
        this.regDt = regDt;
        this.cost = cost;
        this.cache = cache;
        this.member = member;
    }
}
