package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Member;
import numble.webnovel.domain.Novel;
import numble.webnovel.domain.NovelTicket;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.dto.request.NovelTicketChargeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelTicketChargeService {

    private final NovelTicketService novelTicketService;
    private final MemberService memberService;
    private final NovelService novelService;
    private final ChargeValidationService chargeValidationService;

    @Transactional
    public int chargeTicket(Long memberId, Long novelId, int ticketCnt){
        //redis validation
        if(chargeValidationService.isDuplicatedCharge(memberId)){
            throw new CommonException(ExceptionEnum.DUPLICATE_CHARGE_EXCEPTION);
        }
        // TODO modify
        chargeValidationService.saveCharge(memberId, "CHARGE");

        Novel novel = novelService.findNovel(novelId);
        Member member = memberService.findByMemberId(memberId);
        member.buyTicket(novel.getEpisodeCost(), ticketCnt);
        novel.plusPaymentCnt(ticketCnt);

        NovelTicket novelTicket = NovelTicket.createNovelTicket(ticketCnt, ticketCnt, novel.getEpisodeCost(), member, novel);
        novelTicketService.saveUserNovelTicket(novelTicket);

        return ticketCnt;
    }

    public boolean validRequestParam(NovelTicketChargeRequest novelTicketChargeRequest){
        return novelTicketChargeRequest.getMemberId() != null && novelTicketChargeRequest.getNovelId() != null
                && novelTicketChargeRequest.getChargeTicketsCnt() != 0;
    }
}
