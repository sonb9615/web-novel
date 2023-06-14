package numble.webnovel.favoriteNovel.domain;

import lombok.Getter;
import numble.webnovel.member.domain.Member;
import numble.webnovel.novel.domain.Novel;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "favorite_novel")
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
}
