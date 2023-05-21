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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long novelId;

  private String title;
  private String author;
  private Long likeCnt;
  private String novelInfo;
  private long paymentCnt;
  private String novelImg;
  private String genre;
  private int episodeCost;
  private LocalDateTime regDt;
  private LocalDateTime udtDt;

  @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL)
  private List<Episode> episodeList = new ArrayList<>();

  @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL)
  private List<NovelTag> novelTagList = new ArrayList<>();

  @OneToMany(mappedBy = "novel", cascade = CascadeType.ALL)
  private List<NovelTicket> novelTicketList = new ArrayList<>();

  public void setNovelTag(NovelTag tag){
    this.novelTagList.add(tag);
    tag.setNovel(this);
  }

  public void setNovelEpisode(Episode episode){
    this.episodeList.add(episode);
    episode.setNovel(this);
  }

  public void setUserNovelTicket(NovelTicket tickets){
    this.novelTicketList.add(tickets);
    tickets.setNovel(this);
  }

  // 생성 매서드
  public static Novel createNovel(String title, String author, String novelInfo, String novelImg, int episodeCost, String genre) {
    Novel novel = new Novel();
    novel.setTitle(title);
    novel.setAuthor(author);
    novel.setLikeCnt(0L);
    novel.setNovelInfo(novelInfo);
    novel.setPaymentCnt(0);
    novel.setNovelImg(novelImg);
    novel.setEpisodeCost(episodeCost);
    novel.setGenre(genre);
    novel.setRegDt(LocalDateTime.now());
    return novel;
  }

  // 비지니스 로직
  public Long plusLikeCnt(){
    this.likeCnt += 1;
    this.udtDt = LocalDateTime.now();
    return likeCnt;
  }

  public Long minusLikeCnt(){
    this.likeCnt -= 1;
    this.udtDt = LocalDateTime.now();
    return likeCnt;
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
