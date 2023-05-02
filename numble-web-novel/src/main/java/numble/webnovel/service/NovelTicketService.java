package numble.webnovel.service;

import lombok.RequiredArgsConstructor;
import numble.webnovel.domain.Episode;
import numble.webnovel.repository.dto.request.NovelTicketsRequest;
import numble.webnovel.domain.Library;
import numble.webnovel.domain.NovelTicket;
import numble.webnovel.enums.ExceptionEnum;
import numble.webnovel.exceptions.CommonException;
import numble.webnovel.repository.NovelTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelTicketService {

    private final LibraryService libraryService;
    private final UUIDGeneration uuidGeneration;
    private final EpisodeService episodeService;
    private final NovelTicketRepository novelTicketRepository;

    @Transactional
    public void useNovelTickets(String userNo, String novelId, String episodeId){
        List<NovelTicket> novelTicketList = this.findUsableTickets(userNo, novelId);
        //이용권 하나 사용
        NovelTicket ticket = novelTicketList.get(0);
        String ticketId = ticket.useTicket();
        //유저 라이브러리에 에피 저장
        Episode episode = episodeService.findNovelEpisodeById(episodeId);
        Library library = Library.createLibrary(episode, ticket.getMember(), ticket);
        libraryService.saveUserLibrary(library);
    }

    @Transactional
    public void saveUserNovelTickets(NovelTicket novelTicket){
        novelTicketRepository.save(novelTicket);
    }

    @Transactional
    public List<NovelTicket> findUsableTickets(String userNo, String novelId){
        List<NovelTicket> novelTicketList
                = novelTicketRepository.findAllTicketsByNovelIdUserId(userNo, novelId);
        if(novelTicketList.size() == 0){
            throw new CommonException(ExceptionEnum.NOVEL_TICKET_NOT_EXIST_EXCEPTION);
        }
        return novelTicketList;
    }

    public boolean validRequestParam(NovelTicketsRequest reqParam){
        return !reqParam.getUserNo().isEmpty() && !reqParam.getNovelId().isEmpty() && !reqParam.getEpisodeId().isEmpty();
    }
}
