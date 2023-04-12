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
@Table(name = "user_library")
public class UserLibrary {

  @Id
  @Column(name = "episode_id")
  private String episodeId;

  @Id
  @Column(name = "user_no")
  private String userNo;

  @Column(name = "last_page_no")
  private long lastPageNo;

  @Column(name = "read_date")
  private java.sql.Date readDate;

  @Column(name = "star_point")
  private long starPoint;

  public static UserLibrary userLibrary(String episodeId, String userNo, long lastPageNo, Date readDate, long starPoint) {
    UserLibrary userLibrary = new UserLibrary();
    userLibrary.setEpisodeId(episodeId);
    userLibrary.setUserNo(userNo);
    userLibrary.setLastPageNo(lastPageNo);
    userLibrary.setReadDate(readDate);
    userLibrary.setStarPoint(starPoint);
    return userLibrary;
  }
}
