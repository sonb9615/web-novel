package numble.webnovel.library.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.episode.domain.Episode;
import numble.webnovel.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "library", indexes = @Index(name = "idx_library", columnList = "member_id, episode_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Library {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryId;

    private int lastReadPage;
    private LocalDateTime lastReadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_id")
    private Episode episode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Library(Long libraryId, int lastReadPage, LocalDateTime lastReadDate, Episode episode, Member member) {
        this.libraryId = libraryId;
        this.lastReadPage = lastReadPage;
        this.lastReadDate = lastReadDate;
        this.episode = episode;
        this.member = member;
    }

    public static Library createLibrary(Member member, Episode episode){
        return Library.builder()
                .lastReadDate(null)
                .lastReadPage(0)
                .episode(episode)
                .member(member)
                .build();
    }

    public void checkRead(int currentPage){
        if(currentPage == 0) {
            this.lastReadPage = 1;
        }
        this.lastReadDate = LocalDateTime.now();
    }

    public void readNextPage(){
        this.lastReadPage++;
        this.lastReadDate = LocalDateTime.now();
    }

    public void readPreviousPage(){
        this.lastReadPage--;
        this.lastReadDate = LocalDateTime.now();
    }
}
