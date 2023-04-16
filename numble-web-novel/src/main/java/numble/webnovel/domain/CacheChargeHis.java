package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "cache_charge_his")
public class CacheChargeHis {

  @Id
  @Column(name = "payment_no")
  private String paymentNo;
  @Column(name = "user_no")
  private String userNo;
  @Column(name = "date")
  private LocalDateTime date;
  @Column(name = "cost")
  private long cost;
  @Column(name = "cache_cost")
  private long cacheCost;

  public static CacheChargeHis cacheChargeHis(String paymentNo, String userNo, long cost, long cacheCost) {
    CacheChargeHis cacheChargeHis = new CacheChargeHis();
    cacheChargeHis.setPaymentNo(paymentNo);
    cacheChargeHis.setUserNo(userNo);
    cacheChargeHis.setDate(LocalDateTime.now());
    cacheChargeHis.setCost(cost);
    cacheChargeHis.setCacheCost(cacheCost);
    return cacheChargeHis;
  }
}
