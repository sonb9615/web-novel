package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Novel;
import numble.webnovel.domain.NovelTicketChargeInfo;
import numble.webnovel.domain.UserInfo;
import numble.webnovel.domain.UserNovelTickets;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelTicketsChargeService {

    private final UserNovelTicketsService userNovelTicketsService;
    private final UserInfoService userInfoService;
    private final NovelService novelService;
    private final UUIDGeneration uuidGeneration;

    @Transactional
    public void chargeTicket(NovelTicketChargeInfo chargeInfo){
        chargeInfo = getNovelTicketChargeInfo(chargeInfo);
        if(validEnoughCahce(chargeInfo)){
            int chargeTicketsCnt = chargeInfo.getChargeTicketsCnt();
            UserInfo userInfo = userInfoService.findByUserNo(chargeInfo.getUserNo());
            userInfo.setCache(chargeInfo.getUserCache() - chargeTicketsCnt * chargeInfo.getEpisodeCost());
            userInfoService.saveUserInfo(userInfo);
            UserNovelTickets userNovelTickets = UserNovelTickets.userNovelTickets(uuidGeneration.getUUID(), chargeInfo.getUserNo()
                    ,chargeInfo.getNovelId(),chargeTicketsCnt, chargeTicketsCnt, 0, chargeInfo.getEpisodeCost());
            userNovelTicketsService.saveUserNovelTickets(userNovelTickets);
            return;
        }
        //   캐쉬충전 으로 리다이렉팅
    }

    private NovelTicketChargeInfo getNovelTicketChargeInfo(NovelTicketChargeInfo novelTicketChargeInfo){
        int chargeTicketsCnt = novelTicketChargeInfo.getChargeTicketsCnt();
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

    private boolean validEnoughCahce(NovelTicketChargeInfo novelTicketChargeInfo){
        int chargeTicketsCnt = novelTicketChargeInfo.getChargeTicketsCnt();
        int episodeCost = novelTicketChargeInfo.getEpisodeCost();
        int userCache = novelTicketChargeInfo.getUserCache();
        return chargeTicketsCnt * episodeCost <= userCache;
    }
}
