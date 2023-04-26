package numble.webnovel.service

import numble.webnovel.domain.CacheChargeRequest
import numble.webnovel.domain.ChildCode
import numble.webnovel.domain.UserInfo
import numble.webnovel.domain.UserNovelTickets
import numble.webnovel.exceptions.CommonException
import numble.webnovel.repository.UserNovelTicketsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@SpringBootTest
@Rollback
@Transactional
class CommonCodeServiceTest extends Specification{

    @Autowired
    CommonCodeService commonCodeService;
    @Autowired
    CacheChargeService cacheChargeService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UUIDGeneration uuidGeneration;
    @Autowired
    ChargeValidationService chargeValidationService;
    @Autowired
    UserNovelTicketsService userNovelTicketsService;
    @Autowired
    UserNovelTicketsRepository userNovelTicketsRepository;


    def "부모코드가 없으면 자식코드는 저장이 되지 않는다"() {

        given:

        when:
        ChildCode childCode = ChildCode.childCode("CHILD_CODE","test","child_code");
        commonCodeService.saveChildCode(childCode);

        then:
        def e = thrown(CommonException.class)
        println(e);
    }

    def "1초 이내에 중복 충전 건이 들어오면 예외처리 한다"(){
        given:
        String userNo = uuidGeneration.getUUID();
        UserInfo userInfo = UserInfo.createUserInfo(userNo, "origin", "orginPW", "reader", "010-1111-1111", "female","origin@email.com",0);
        userInfoService.saveUserInfo(userInfo);
        chargeValidationService.saveCharge(userNo, userNo);

        when:
        CacheChargeRequest cacheChargeInfo = CacheChargeRequest.cacheChargeInfo(userNo, "testEpi", 100);
        cacheChargeService.cacheCharge(cacheChargeInfo);

        then:
        def e = thrown(CommonException.class);
        println(e);
    }

    def "1초 이후에 충전은 허용한다."(){
        given:
        String userNo = uuidGeneration.getUUID();
        UserInfo userInfo = UserInfo.createUserInfo(userNo, "origin_1", "orginPW", "reader", "010-1111-1111", "female","origin@email.com",0);
        userInfoService.saveUserInfo(userInfo);
        chargeValidationService.saveCharge(userNo, userNo);

        when:
        Thread.sleep(2000);
        CacheChargeRequest cacheChargeInfo = CacheChargeRequest.cacheChargeInfo(userNo, "testEpi", 100);
        cacheChargeService.cacheCharge(cacheChargeInfo);

        then:
        UserInfo info = userInfoService.findByUserNo(userNo);
        info.getCache() == 1000;
        userInfoService.deleteByUserNo(userNo);
    }

    def "List로 받은 것은 하나의 로우가 영속성을 가지고 있을까"(){
        given:
        String userNo = "ef21dc73ec124a93b1d8896c08374de7";
        String novelId = "45ed40d9224a4d70bc1fff6f8306881c";
        when:
        List<UserNovelTickets> userNovelTicketsList = userNovelTicketsService.findUsableTickets(userNo, novelId);
        String ticketId = userNovelTicketsList.get(0).useTicket();
        then:
        List<UserNovelTickets> userNovelTicketsList_1 = userNovelTicketsService.findUsableTickets(userNo, novelId);
        for(UserNovelTickets t : userNovelTicketsList_1){
            println(t.getUsableTicketCnt());
        }
        userNovelTicketsList_1.get(0).getUsableTicketCnt() == 2;
    }


}
