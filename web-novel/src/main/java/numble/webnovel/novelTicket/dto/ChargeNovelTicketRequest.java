package numble.webnovel.novelTicket.dto;

import lombok.Getter;
import numble.webnovel.member.domain.Member;
import numble.webnovel.novelTicket.domain.NovelTicket;

import java.time.LocalDateTime;

@Getter
public class ChargeNovelTicketRequest {

    private int chargeTicketCnt;

    public NovelTicket toNovelTicket(Member member, Long novelId, int ticketCost){

        return NovelTicket.builder()
                .novelId(novelId)
                .usableTicketCnt(chargeTicketCnt)
                .usedTicketCnt(0)
                .ticketCost(ticketCost)
                .regDt(LocalDateTime.now())
                .member(member)
                .build();
    }
}
