package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  public void setMember(Member info){
    this.member = info;
    info.getCacheChargeHisList().add(this);
  }

  public static CacheChargeHis createCacheChargeHis(Member member, int cost, int cache) {
    CacheChargeHis cacheChargeHis = new CacheChargeHis();
    cacheChargeHis.setDate(LocalDateTime.now());
    cacheChargeHis.setCost(cost);
    cacheChargeHis.setCache(cache);
    cacheChargeHis.setMember(member);
    return cacheChargeHis;
  }
}
