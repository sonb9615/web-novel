package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.NovelTicketsRequest;
import numble.webnovel.domain.NovelTicketsResponse;
import numble.webnovel.domain.UserLibrary;
import numble.webnovel.domain.UserNovelTickets;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.UserNovelTicketsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserNovelTicketsService {

    private final UserNovelTicketsRepository userNovelTicketsRepository;
    private final NovelService novelService;
    private final UserLibraryService userLibraryService;
    private final UUIDGeneration uuidGeneration;

    @Transactional
    public NovelTicketsResponse useNovelTickets(NovelTicketsRequest novelTicketsRequest){
        String userNo = novelTicketsRequest.getUserNo();
        String novelId = novelTicketsRequest.getNovelId();
        String episodeId = novelTicketsRequest.getEpisodeId();
        List<UserNovelTickets> userNovelTicketsList = this.findUsableTickets(userNo, novelId);
        //이용권 하나 사용
        this.deductTicket(userNovelTicketsList.get(0));
        //유저 라이브러리에 에피 저장
        UserLibrary userLibrary = UserLibrary.userLibrary(uuidGeneration.getUUID(), episodeId, userNo, 0, LocalDateTime.now(), 0);
        userLibraryService.saveUserLibrary(userLibrary);
        //구매수 +1;
        novelService.plusNovelLickCnt(novelId, 1);
        return NovelTicketsResponse.novelTicketsResponse(novelId, episodeId);
    }

    @Transactional
    public void deductTicket(UserNovelTickets ticket){
        ticket.setUsableTicketCnt(ticket.getUsableTicketCnt() - 1);
        ticket.setUsedTicketCnt(ticket.getUsedTicketCnt() + 1);
        this.saveUserNovelTickets(ticket);
    }

    @Transactional
    public void saveUserNovelTickets(UserNovelTickets userNovelTickets){
        userNovelTicketsRepository.save(userNovelTickets);
    }

    @Transactional
    public List<UserNovelTickets> findUsableTickets(String userNo, String novelId){
        List<UserNovelTickets> userNovelTicketsList
                = userNovelTicketsRepository.findUsableTicketsByNovelIdUserId(userNo, novelId);
        if(userNovelTicketsList.size() == 0){
            throw new CommonException(ExceptionEnum.NOVEL_TICKET_NOT_EXIST_EXCEPTION);
        }
        return userNovelTicketsList;
    }

    public boolean validRequestParam(NovelTicketsRequest reqParam){
        return !reqParam.getUserNo().isEmpty() && !reqParam.getNovelId().isEmpty() && !reqParam.getEpisodeId().isEmpty();
    }
}
