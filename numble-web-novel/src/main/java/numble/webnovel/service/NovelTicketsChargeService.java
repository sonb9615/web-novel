package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.*;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.dto.request.NovelTicketChargeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelTicketsChargeService {

    private final UserNovelTicketsService userNovelTicketsService;
    private final UserInfoService userInfoService;
    private final NovelService novelService;
    private final UUIDGeneration uuidGeneration;
    private final ChargeValidationService chargeValidationService;

    @Transactional
    public int chargeTicket(String userNo, String novelId, int ticketCnt){
        //redis validation
        if(chargeValidationService.isDuplicatedCharge(userNo)){
            throw new CommonException(ExceptionEnum.DUPLICATE_CHARGE_EXCEPTION);
        }
        chargeValidationService.saveCharge(userNo, userNo);

        Novel novel = novelService.findNovel(novelId);
        UserInfo userInfo = userInfoService.findByUserNo(userNo);
        userInfo.buyTicket(novel.getEpisodeCost(), ticketCnt);
        novel.plusPaymentCnt(ticketCnt);

        UserNovelTickets userNovelTickets = UserNovelTickets.createUserNovelTickets(uuidGeneration.getUUID(), ticketCnt, ticketCnt
                ,novel.getEpisodeCost(),userInfo, novel);
        userNovelTicketsService.saveUserNovelTickets(userNovelTickets);

        return ticketCnt;
    }

    public boolean validRequestParam(NovelTicketChargeRequest novelTicketChargeRequest){
        return !novelTicketChargeRequest.getUserNo().isEmpty() && !novelTicketChargeRequest.getNovelId().isEmpty()
                && novelTicketChargeRequest.getChargeTicketsCnt() != 0;
    }
}
