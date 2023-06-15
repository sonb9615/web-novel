package numble.webnovel.novelTicket.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "novel_ticket")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NovelTicket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private Long novelId;
    private int usableTicketCnt;
    private int usedTicketCnt;
    private int ticketCost;
    private LocalDateTime regDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;



}
