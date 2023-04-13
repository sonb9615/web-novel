package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "novel")
public class Novel {

  @Id
  @Column(name = "novel_id")
  private String novelId;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "like_cnt")
  private long likeCnt;

  @Column(name = "novel_info")
  private String novelInfo;

  @Column(name = "payment_cnt")
  private long paymentCnt;

  @Column(name = "novel_img")
  private String novelImg;

  @Column(name = "reg_dt")
  private LocalDateTime regDt;

  @Column(name = "udt_dt")
  private LocalDateTime udtDt;

  public static Novel novel(String novelId, String title, String author, long likeCnt, String novelInfo, long paymentCnt, String novelImg) {
    Novel novel = new Novel();
    novel.setNovelId(novelId);
    novel.setTitle(title);
    novel.setLikeCnt(likeCnt);
    novel.setNovelInfo(novelInfo);
    novel.setPaymentCnt(paymentCnt);
    novel.setNovelImg(novelImg);
    novel.setRegDt(LocalDateTime.now());
    return novel;
  }
}
