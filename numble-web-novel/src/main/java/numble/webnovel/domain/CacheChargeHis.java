package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

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
  private java.sql.Date date;
  @Column(name = "cost")
  private long cost;
  @Column(name = "cache_cost")
  private long cacheCost;

  public static CacheChargeHis cacheChargeHis(String paymentNo, String userNo, Date date, long cost, long cacheCost) {
    CacheChargeHis cacheChargeHis = new CacheChargeHis();
    cacheChargeHis.setPaymentNo(paymentNo);
    cacheChargeHis.setUserNo(userNo);
    cacheChargeHis.setDate(date);
    cacheChargeHis.setCost(cost);
    cacheChargeHis.setCacheCost(cacheCost);
    return cacheChargeHis;
  }

  @Override
  public String toString() {
    return "CacheChargeHis{" +
            "paymentNo='" + paymentNo + '\'' +
            ", userNo='" + userNo + '\'' +
            ", date=" + date +
            ", cost=" + cost +
            ", cacheCost=" + cacheCost +
            '}';
  }
}
