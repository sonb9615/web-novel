package numble.webnovel.episode.domain;

import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(nullable = false)
    private int episodeNo;
    @Column(nullable = false)
    private String episodeTitle;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int totalPage;
    private boolean freeYn;
    @Column(nullable = false)
    private int capacity;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @Builder
    public Episode(Long episodeId, int episodeNo, String episodeTitle, String content, int totalPage, boolean freeYn, int capacity, LocalDateTime regDt, LocalDateTime udtDt, Novel novel) {
        this.episodeId = episodeId;
        this.episodeNo = episodeNo;
        this.episodeTitle = episodeTitle;
        this.content = content;
        this.totalPage = totalPage;
        this.freeYn = freeYn;
        this.capacity = capacity;
        this.regDt = regDt;
        this.udtDt = udtDt;
        this.novel = novel;
    }
}
