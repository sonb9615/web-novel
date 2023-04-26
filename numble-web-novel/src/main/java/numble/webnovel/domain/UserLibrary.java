package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_library", indexes = @Index(name = "idx_user_library", columnList = "user_no, episode_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLibrary {

  @Id
  @Column(name = "id")
  private String id;

  private int lastPageNo;
  private LocalDateTime readDt;
  private int starPoint;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_no")
  private UserInfo userInfo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_id")
  private NovelEpisode novelEpisode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ticket_no")
  private UserNovelTickets userNovelTickets;

  public void setUserInfo(UserInfo info){
    this.userInfo = info;
    info.getUserLibraryList().add(this);
  }

  public void setNovelEpisode(NovelEpisode episode){
    this.novelEpisode = episode;
    episode.getUserLibraryList().add(this);
  }

  public void setUserNovelTickets(UserNovelTickets tickets){
    this.userNovelTickets = tickets;
    tickets.getUserLibraryList().add(this);
  }

  //생성 매서드
  public static UserLibrary createUserLibrary(String id, NovelEpisode novelEpisode, UserInfo userInfo, UserNovelTickets userNovelTickets) {
    UserLibrary userLibrary = new UserLibrary();
    userLibrary.setId(id);
    userLibrary.setLastPageNo(0);
    userLibrary.setReadDt(LocalDateTime.now());
    userLibrary.setStarPoint(-1);
    userLibrary.setNovelEpisode(novelEpisode);
    userLibrary.setUserInfo(userInfo);
    userLibrary.setUserNovelTickets(userNovelTickets);
    return userLibrary;
  }

  //비지니스 로직
  public void updateStarPoint(int point){
    this.starPoint = point;
  }

  public void updateLastReadPage(int page){
    this.lastPageNo = page;
    this.readDt = LocalDateTime.now();
  }
}
