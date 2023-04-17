package numble.webnovel.service

import numble.webnovel.domain.UserNovelTickets
import numble.webnovel.exceptions.CommonException
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

    def "사용가능한 티켓이 없는 경우 에러 발생"(){
        given:

        when:
        List<UserNovelTickets> userNovelTickets = userNovelTicketsService.findUsableTickets("test", "test");
        then:
        def e = thrown(CommonException.class);
        println(e);
    }


}
