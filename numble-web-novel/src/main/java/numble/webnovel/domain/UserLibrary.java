package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_library", indexes = @Index(name = "idx_user_library", columnList = "user_no, episode_id"))
public class UserLibrary {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "episode_id")
  private String episodeId;

  @Column(name = "user_no")
  private String userNo;

  @Column(name = "last_page_no")
  private int lastPageNo;

  @Column(name = "read_dt")
  private LocalDateTime readDt;

  @Column(name = "star_point")
  private int starPoint;

  private String novelId;

  private int page;

  private String title;

  public static UserLibrary userLibrary(String id, String episodeId, String userNo, int lastPageNo, LocalDateTime readDate, int starPoint) {
    UserLibrary userLibrary = new UserLibrary();
    userLibrary.setId(id);
    userLibrary.setEpisodeId(episodeId);
    userLibrary.setUserNo(userNo);
    userLibrary.setLastPageNo(lastPageNo);
    userLibrary.setReadDt(readDate);
    userLibrary.setStarPoint(starPoint);
    return userLibrary;
  }
}
