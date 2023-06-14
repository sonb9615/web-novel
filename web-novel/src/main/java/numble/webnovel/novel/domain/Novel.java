package numble.webnovel.novel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.genre.domain.Genre;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "novel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Novel {

    @Id
    @Column(name = "novel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long novelId;

    private String title;
    private String author;
    private String novelInfo;
    private long paymentCnt;
    private String novelImg;
    private int episodeCost;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

}
