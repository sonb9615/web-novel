package numble.webnovel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "library", indexes = @Index(name = "idx_library", columnList = "member_id, episode_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Library {

  @Id
  @Column(name = "library_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long libraryId;

  private int lastReadPage;
  private LocalDateTime lastReadDate;
  private int starPoint;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_id")
  private Episode episode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ticket_id")
  private NovelTicket novelTicket;

  public void setMember(Member info){
    this.member = info;
    info.getLibraryList().add(this);
  }

  public void setEpisode(Episode episode){
    this.episode = episode;
    episode.getLibraryList().add(this);
  }

  public void setNovelTicket(NovelTicket ticket){
    this.novelTicket = ticket;
    ticket.getLibraryList().add(this);
  }

  //생성 매서드
  public static Library createLibrary(Episode episode, Member member, NovelTicket novelTicket) {
    Library library = new Library();
    library.setLastReadPage(0);
    library.setLastReadDate(LocalDateTime.now());
    library.setStarPoint(-1);
    library.setEpisode(episode);
    library.setMember(member);
    library.setNovelTicket(novelTicket);
    return library;
  }

  //비지니스 로직
  public void updateStarPoint(int point){
    this.starPoint = point;
  }

  public void updateLastReadPage(int page){
    this.lastReadPage = page;
    this.lastReadDate = LocalDateTime.now();
  }
}
