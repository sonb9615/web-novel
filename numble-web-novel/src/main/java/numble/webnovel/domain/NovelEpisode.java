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
@Table(name = "novel_episode")
public class NovelEpisode {

  @Id
  @Column(name = "episode_id")
  private String episodeId;

  @Column(name = "novel_id")
  private String novelId;

  @Column(name = "episode_no")
  private long episodeNo;

  @Column(name = "episode_title")
  private String episodeTitle;

  @Column(name = "content")
  private String content;

  @Column(name = "page")
  private long page;

  @Column(name = "free_yn")
  private boolean freeYn;

  @Column(name = "capacity")
  private long capacity;

  @Column(name = "reg_dt")
  private LocalDateTime regDt;

  @Column(name = "udt_dt")
  private LocalDateTime udtDt;

  public NovelEpisode novelEpisode(String episodeId, String novelId, long episodeNo, String episodeTitle, String content, long page, long capacity) {
    NovelEpisode novelEpisode = new NovelEpisode();
    novelEpisode.setEpisodeId(episodeId);
    novelEpisode.setNovelId(novelId);
    novelEpisode.setEpisodeNo(episodeNo);
    novelEpisode.setEpisodeTitle(episodeTitle);
    novelEpisode.setContent(content);
    novelEpisode.setPage(page);
    novelEpisode.setFreeYn(false);
    novelEpisode.setCapacity(capacity);
    novelEpisode.setRegDt(LocalDateTime.now());
    return novelEpisode;
  }
}
