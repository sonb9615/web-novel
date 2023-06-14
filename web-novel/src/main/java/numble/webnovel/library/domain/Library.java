package numble.webnovel.library.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.episode.domain.Episode;
import numble.webnovel.novelTicket.domain.NovelTicket;

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
    @JoinColumn(name="ticket_id")
    private NovelTicket novelTicket;


}
