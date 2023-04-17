package numble.webnovel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_library")
public class UserLibrary {

  @Id
  @Column(name = "id")
  private Long id;

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

  public static UserLibrary userLibrary(String episodeId, String userNo, int lastPageNo, LocalDateTime readDate, int starPoint) {
    UserLibrary userLibrary = new UserLibrary();
    userLibrary.setEpisodeId(episodeId);
    userLibrary.setUserNo(userNo);
    userLibrary.setLastPageNo(lastPageNo);
    userLibrary.setReadDt(readDate);
    userLibrary.setStarPoint(starPoint);
    return userLibrary;
  }
}
