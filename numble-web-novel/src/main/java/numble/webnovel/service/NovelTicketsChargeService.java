package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.*;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
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
    public ChargeApiResponse chargeTicket(NovelTicketChargeInfo chargeInfo){
        String userNo = chargeInfo.getUserNo();
        if(chargeValidationService.isDuplicatedCharge(userNo)){
            throw new CommonException(ExceptionEnum.DUPLICATE_CHARGE_EXCEPTION);
        }
        chargeValidationService.saveCharge(userNo, userNo);
        chargeInfo = getNovelTicketChargeInfo(chargeInfo);
        if(validEnoughCache(chargeInfo)){
            int chargeTicketsCnt = chargeInfo.getChargeTicketsCnt();
            UserInfo userInfo = userInfoService.findByUserNo(chargeInfo.getUserNo());
            userInfo.setCache(chargeInfo.getUserCache() - chargeTicketsCnt * chargeInfo.getEpisodeCost());
            userInfoService.saveUserInfo(userInfo);
            UserNovelTickets userNovelTickets = UserNovelTickets.userNovelTickets(uuidGeneration.getUUID(), chargeInfo.getUserNo()
                    ,chargeInfo.getNovelId(),chargeTicketsCnt, chargeTicketsCnt, 0, chargeInfo.getEpisodeCost());
            userNovelTicketsService.saveUserNovelTickets(userNovelTickets);
            ChargeApiResponse chargeApiResponse = new ChargeApiResponse();
            chargeApiResponse.setResult("SUCESS");
            return chargeApiResponse;
        }
        throw new CommonException(ExceptionEnum.CACHE_SHORTAGE_EXCEPTION);
    }

    @Transactional
    public NovelTicketChargeInfo getNovelTicketChargeInfo(NovelTicketChargeInfo novelTicketChargeInfo){
        // 단가가 얼마인지
        Novel novel = novelService.findNovel(novelTicketChargeInfo.getNovelId());
        int episodeCost = novel.getEpisodeCost();
        // 캐시 충분한지
        UserInfo userInfo = userInfoService.findByUserNo(novelTicketChargeInfo.getUserNo());
        int userCache = userInfo.getCache();
        novelTicketChargeInfo.setEpisodeCost(episodeCost);
        novelTicketChargeInfo.setUserCache(userCache);
        return novelTicketChargeInfo;
    }

    @Transactional
    public boolean validEnoughCache(NovelTicketChargeInfo novelTicketChargeInfo){
        int chargeTicketsCnt = novelTicketChargeInfo.getChargeTicketsCnt();
        int episodeCost = novelTicketChargeInfo.getEpisodeCost();
        int userCache = novelTicketChargeInfo.getUserCache();
        return chargeTicketsCnt * episodeCost <= userCache;
    }

    public boolean validRequestParam(NovelTicketChargeInfo novelTicketChargeInfo){
        return !novelTicketChargeInfo.getUserNo().isEmpty() && !novelTicketChargeInfo.getNovelId().isEmpty()
                && novelTicketChargeInfo.getChargeTicketsCnt() != 0;
    }
}
