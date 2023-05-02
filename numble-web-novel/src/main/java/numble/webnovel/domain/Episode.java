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
@Table(name = "episode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Episode {

  @Id
  @Column(name = "episode_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long episodeId;

  private int episodeNo;
  private String episodeTitle;
  private String content;
  private int totalPage;
  private boolean freeYn;
  private int capacity;
  private LocalDateTime regDt;
  private LocalDateTime udtDt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "novel_id")
  private Novel novel;

  @OneToMany(mappedBy = "episode")
  private List<Library> libraryList = new ArrayList<>();

  public void setNovel(Novel novel){
    this.novel = novel;
    novel.getEpisodeList().add(this);
  }

  public Episode createEpisode(int episodeNo, String episodeTitle, String content, boolean freeYn ,int totalPage, int capacity) {
    Episode episode = new Episode();
    episode.setEpisodeNo(episodeNo);
    episode.setEpisodeTitle(episodeTitle);
    episode.setContent(content);
    episode.setFreeYn(freeYn);
    episode.setTotalPage(totalPage);
    episode.setCapacity(capacity);
    episode.setRegDt(LocalDateTime.now());
    return episode;
  }
}
