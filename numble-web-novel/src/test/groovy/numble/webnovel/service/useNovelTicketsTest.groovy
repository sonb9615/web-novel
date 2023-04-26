package numble.webnovel.service

import numble.webnovel.domain.NovelEpisode
import numble.webnovel.domain.UserNovelTickets
import numble.webnovel.exceptions.CommonException
import numble.webnovel.repository.NovelEpisodeRepository
import numble.webnovel.repository.NovelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@SpringBootTest
@Rollback
@Transactional
class useNovelTicketsTest extends Specification{

    @Autowired
    UserNovelTicketsService userNovelTicketsService;
    @Autowired
    NovelEpisodeRepository novelEpisodeRepository;

    def "사용가능한 티켓이 없는 경우 에러 발생"(){
        given:

        when:
        List<UserNovelTickets> userNovelTickets = userNovelTicketsService.findUsableTickets("test", "test");
        then:
        def e = thrown(CommonException.class);
        println(e);
    }

    def "에피소드 출력"(){
        given:
        String epi_id = "test_epi_id_1";
        when:
        NovelEpisode episode = novelEpisodeRepository.findById(epi_id);
        then:
        episode.getNovel().getEpisodeCost() == 100;
    }

    def "유저 라이브러리 리스트 출력"(){
        given:
        String userNo = "ef21dc73ec124a93b1d8896c08374de7";
        String novelId = "45ed40d9224a4d70bc1fff6f8306881c";
        when:
        List<UserNovelTickets> userNovelTicketsList = userNovelTicketsService.findUsableTickets(userNo, novelId);
        then:
        userNovelTicketsList.size() == 2;
    }
}
