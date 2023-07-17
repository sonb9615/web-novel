package numble.webnovel.favoriteNovel.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.member.domain.Member;
import numble.webnovel.novel.domain.Novel;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "favorite_novel", indexes = @Index(name = "idx_favorite_novel", columnList = "member_id, novel_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteNovel {

    @Id
    @Column(name = "favorite_novel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteNovelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public FavoriteNovel(Long favoriteNovelId, Novel novel, Member member) {
        this.favoriteNovelId = favoriteNovelId;
        this.novel = novel;
        this.member = member;
    }
}
