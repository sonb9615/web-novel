package numble.webnovel.episode.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.novel.domain.Novel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
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

}
