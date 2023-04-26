package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "cache_charge_his")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CacheChargeHis {

  @Id
  @Column(name = "payment_no")
  private String paymentNo;
  @Column(name = "date")
  private LocalDateTime date;
  @Column(name = "cost")
  private long cost;
  @Column(name = "cache_cost")
  private long cacheCost;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_no")
  private UserInfo userInfo;

  public void setUserInfo(UserInfo info){
    this.userInfo = info;
    info.getCacheChargeHisList().add(this);
  }

  public static CacheChargeHis createCacheChargeHis(String paymentNo, UserInfo userInfo, long cost, long cacheCost) {
    CacheChargeHis cacheChargeHis = new CacheChargeHis();
    cacheChargeHis.setPaymentNo(paymentNo);
    cacheChargeHis.setDate(LocalDateTime.now());
    cacheChargeHis.setCost(cost);
    cacheChargeHis.setCacheCost(cacheCost);
    cacheChargeHis.setUserInfo(userInfo);
    return cacheChargeHis;
  }
}
