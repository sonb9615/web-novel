package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "novel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Novel {

  @Id
  @Column(name = "novel_id")
  private String novelId;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "like_cnt")
  private int likeCnt;

  @Column(name = "novel_info")
  private String novelInfo;

  @Column(name = "payment_cnt")
  private long paymentCnt;

  @Column(name = "novel_img")
  private String novelImg;

  @Column(name = "episode_cost")
  private int episodeCost;

  @Column(name = "reg_dt")
  private LocalDateTime regDt;

  @Column(name = "udt_dt")
  private LocalDateTime udtDt;

  @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL)
  private List<NovelEpisode> novelEpisodeList = new ArrayList<>();

  @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL)
  private List<NovelTag> novelTagList = new ArrayList<>();

  @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL)
  private List<UserNovelTickets> userNovelTicketsList = new ArrayList<>();

  public void setNovelTag(NovelTag tag){
    this.novelTagList.add(tag);
    tag.setNovel(this);
  }

  public void setNovelEpisode(NovelEpisode episode){
    this.novelEpisodeList.add(episode);
    episode.setNovel(this);
  }

  public void setUserNovelTicket(UserNovelTickets tickets){
    this.userNovelTicketsList.add(tickets);
    tickets.setNovel(this);
  }

  // 생성 매서드
  public static Novel novel(String novelId, String title, String author, int likeCnt
          , String novelInfo, long paymentCnt, String novelImg, int episodeCost
          , List<NovelTag> novelTags) {
    Novel novel = new Novel();
    novel.setNovelId(novelId);
    novel.setTitle(title);
    novel.setAuthor(author);
    novel.setLikeCnt(likeCnt);
    novel.setNovelInfo(novelInfo);
    novel.setPaymentCnt(paymentCnt);
    novel.setNovelImg(novelImg);
    novel.setEpisodeCost(episodeCost);
    for(NovelTag novelTag : novelTags){
      novel.setNovelTag(novelTag);
    }
    return novel;
  }

  // 비지니스 로직
  public int plusLikeCnt(){
    return getLikeCnt() + 1;
  }

  public int minusLikeCnt(){
    return getLikeCnt() - 1;
  }

  public void plusPaymentCnt(int cnt){
    this.paymentCnt += cnt;
  }
}
