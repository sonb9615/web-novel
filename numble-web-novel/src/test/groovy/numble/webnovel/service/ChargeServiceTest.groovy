package numble.webnovel.service

import numble.webnovel.domain.Member
import numble.webnovel.domain.Novel
import numble.webnovel.domain.NovelTag
import numble.webnovel.exceptions.CommonException
import numble.webnovel.repository.dto.request.NovelTicketChargeRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@SpringBootTest
@Rollback
@Transactional
class ChargeServiceTest extends Specification{

    @Autowired
    CacheChargeService cacheChargeService;
    @Autowired
    MemberService userInfoService;
    @Autowired
    UUIDGeneration uuidGeneration;
    @Autowired
    ChargeValidationService chargeValidationService;
    @Autowired
    NovelService novelService;
    @Autowired
    NovelTicketChargeService novelTicketsChargeService;

    def "1초 이내에 중복 충전 건이 들어오면 예외처리 한다"(){
        given:
        def userNo = "e898ef0f3fc24ce68e48cc82010353fe";
        chargeValidationService.saveCharge(userNo, userNo);

        when:
        cacheChargeService.cacheCharge(userNo, 100);

        then:
        def e = thrown(CommonException.class);
        println(e);
    }

    def "1초 이후에 충전은 허용한다."(){
        given:
        def userNo = "e898ef0f3fc24ce68e48cc82010353fe";
        chargeValidationService.saveCharge(userNo, userNo);

        when:
        Thread.sleep(2000);
        cacheChargeService.cacheCharge(userNo, 100);

        then:
        Member info = userInfoService.findByMemberId(userNo);
        info.getCache() == 100;
    }


    def "캐쉬가 충분한 상태에서 이용권 충전"(){
        given:
        String userNo = uuidGeneration.getUUID();
        Member userInfo = Member.createMember(userNo, "testUserId", "qwer1243", "reader", "010-1111-1111", "female","testUser@email.com");
        userInfo.chargeCache(1000);
        userInfoService.saveUserInfo(userInfo);
        String novelId = uuidGeneration.getUUID();
        List<NovelTag> novelTags = new ArrayList<>();
        Novel novel = Novel.novel(novelId, "title", "author", 0, "description", 0, "novelImgUrl", 200, novelTags ) ;
        novelService.saveNovel(novel);

        when:
        novelTicketsChargeService.chargeTicket(userNo, novelId, 3);

        then:
        Member info = userInfoService.findByMemberId(userNo);
        println(info.getCache());
        info.getCache() == 400;
    }

    def "이용권 충전시 캐쉬가 충분하지 않으면 에러"(){
        given:
        String userNo = uuidGeneration.getUUID();
        Member userInfo = Member.createMember(userNo, "origin", "orginPW", "reader", "010-1111-1111", "female","origin@email.com",0);
        userInfoService.saveUserInfo(userInfo);
        String novelId = uuidGeneration.getUUID();
        Novel novel = Novel.novel(novelId, "어린왕자", "생텍쥐페리", 0, "어린왕자 입니다.", 0, "novelImgUrl", 100) ;
        novelService.saveNovel(novel);

        when:
        NovelTicketChargeRequest novelTicketChargeInfo = NovelTicketChargeRequest.novelTicketChargeInfo(userNo, novelId, 3);
        novelTicketsChargeService.chargeTicket(novelTicketChargeInfo);

        then:
        def e = thrown(CommonException.class);
        println(e);
    }

}
