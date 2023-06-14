package numble.webnovel.cacheChargeHis.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import numble.webnovel.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "cache_charge_his")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CacheChargeHis {

    @Id
    @Column(name = "charge_his_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String chargeHisId;

    private LocalDateTime date;
    private int cost;
    private int cache;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
