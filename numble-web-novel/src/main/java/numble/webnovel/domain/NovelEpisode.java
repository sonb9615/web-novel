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
@Table(name = "novel_episode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NovelEpisode {

  @Id
  @Column(name = "episode_id")
  private String episodeId;

  @Column(name = "episode_no")
  private long episodeNo;

  @Column(name = "episode_title")
  private String episodeTitle;

  @Column(name = "content")
  private String content;

  @Column(name = "page")
  private int page;

  @Column(name = "free_yn")
  private boolean freeYn;

  @Column(name = "capacity")
  private long capacity;

  @Column(name = "reg_dt")
  private LocalDateTime regDt;

  @Column(name = "udt_dt")
  private LocalDateTime udtDt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "novel_id")
  private Novel novel;

  @OneToMany(mappedBy = "novelEpisode")
  private List<UserLibrary> userLibraryList = new ArrayList<>();

  public void setNovel(Novel novel){
    this.novel = novel;
    novel.getNovelEpisodeList().add(this);
  }

  public NovelEpisode createNovelEpisode(String episodeId, long episodeNo, String episodeTitle, String content, int page, long capacity) {
    NovelEpisode novelEpisode = new NovelEpisode();
    novelEpisode.setEpisodeId(episodeId);
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
