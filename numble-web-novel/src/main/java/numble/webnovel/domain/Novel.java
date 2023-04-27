package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

  @Column(name = "genre")
  private String genre;

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
  public static Novel createNovel(String novelId, String title, String author
          , String novelInfo, String novelImg, int episodeCost, String genre) {
    Novel novel = new Novel();
    novel.setNovelId(novelId);
    novel.setTitle(title);
    novel.setAuthor(author);
    novel.setLikeCnt(0);
    novel.setNovelInfo(novelInfo);
    novel.setPaymentCnt(0);
    novel.setNovelImg(novelImg);
    novel.setEpisodeCost(episodeCost);
    novel.setGenre(genre);
    novel.setRegDt(LocalDateTime.now());
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

  // title, author 는 수정 불가
  public void updateNovel(String info, String img, int cost, String genre){
    if(!Objects.equals(this.novelInfo, info)) this.novelInfo = info;
    if(!Objects.equals(this.novelImg, img)) this.novelImg = img;
    if(!Objects.equals(this.episodeCost, cost)) this.episodeCost = cost;
    if(!Objects.equals(this.genre, genre)) this.genre = genre;
    this.udtDt = LocalDateTime.now();
  }
}
