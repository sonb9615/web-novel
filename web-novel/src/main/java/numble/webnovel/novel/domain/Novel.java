package numble.webnovel.novel.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.novel.dto.NovelUpdateRequest;
import numble.webnovel.novel.enums.Genre;
import numble.webnovel.novel.enums.SerialStatus;

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

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String novelInfo;
    private long paymentCnt;
    @Column(nullable = false)
    private String novelImg;
    @Column(nullable = false)
    private int episodeCost;
    private LocalDateTime regDt;
    private LocalDateTime udtDt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SerialStatus serialStatus;

    @Builder
    public Novel(Long novelId, String title, String author, String novelInfo, long paymentCnt, String novelImg, int episodeCost, Genre genre, SerialStatus serialStatus, LocalDateTime regDt, LocalDateTime udtDt) {
        this.novelId = novelId;
        this.title = title;
        this.author = author;
        this.novelInfo = novelInfo;
        this.paymentCnt = paymentCnt;
        this.novelImg = novelImg;
        this.episodeCost = episodeCost;
        this.genre = genre;
        this.serialStatus = serialStatus;
        this.regDt = regDt;
        this.udtDt = udtDt;
    }

    public void updateNovel(NovelUpdateRequest request){
        this.serialStatus = SerialStatus.getNovelStatus(request.getSerialStatus());
        this.novelInfo = request.getNovelInfo();
        this.novelImg = request.getNovelImg();
        this.udtDt = LocalDateTime.now();
    }

    public void plusPaymentCnt(){
        this.paymentCnt++;
    }
}
