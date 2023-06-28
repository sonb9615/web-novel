package numble.webnovel.novelTicket.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.webnovel.member.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "novel_ticket", indexes = @Index(name = "idx_novel_ticket", columnList = "member_id, novel_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NovelTicket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @Column(name = "novel_id", nullable = false)
    private Long novelId;
    private int usableTicketCnt;
    private int usedTicketCnt;
    private int ticketCost;
    private LocalDateTime regDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public NovelTicket(Long ticketId, Long novelId, int usableTicketCnt, int usedTicketCnt, int ticketCost, LocalDateTime regDt, Member member) {
        this.ticketId = ticketId;
        this.novelId = novelId;
        this.usableTicketCnt = usableTicketCnt;
        this.usedTicketCnt = usedTicketCnt;
        this.ticketCost = ticketCost;
        this.regDt = regDt;
        this.member = member;
    }

    public void useNovelTicket(){
        this.usableTicketCnt -= 1;
        this.usedTicketCnt += 1;
    }

    public boolean isEnoughNovelTicket(){
        return usableTicketCnt >= 1;
    }

    public void chargeNovelTicket(int chargeTicketCnt){
        this.usableTicketCnt += chargeTicketCnt;
    }
}
