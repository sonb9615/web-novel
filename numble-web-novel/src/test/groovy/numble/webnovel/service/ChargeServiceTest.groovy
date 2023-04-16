package numble.webnovel.service

import numble.webnovel.domain.CacheCargeInfo
import numble.webnovel.domain.Novel
import numble.webnovel.domain.NovelTicketChargeInfo
import numble.webnovel.domain.UserInfo
import numble.webnovel.exceptions.CommonException
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
    NovelService novelService;
    @Autowired
    NovelTicketsChargeService novelTicketsChargeService;

    def "1초 이내에 중복 충전 건이 들어오면 예외처리 한다"(){
        given:
        String userNo = uuidGeneration.getUUID();
        UserInfo userInfo = UserInfo.userInfo(userNo, "origin", "orginPW", "reader", "010-1111-1111", "female","origin@email.com",0);
        userInfoService.saveUserInfo(userInfo);
        chargeValidationService.saveCharge(userNo, userNo);

        when:
        CacheCargeInfo cacheCargeInfo = CacheCargeInfo.cacheCargeInfo(userNo, "testEpi", 100);
        cacheChargeService.cacheCharge(cacheCargeInfo);

        then:
        def e = thrown(CommonException.class);
        println(e);
        userInfoService.deleteByUserNo(userNo);
    }

    def "1초 이후에 충전은 허용한다."(){
        given:
        String userNo = uuidGeneration.getUUID();
        UserInfo userInfo = UserInfo.userInfo(userNo, "origin_1", "orginPW", "reader", "010-1111-1111", "female","origin@email.com",0);
        userInfoService.saveUserInfo(userInfo);
        chargeValidationService.saveCharge(userNo, userNo);

        when:
        Thread.sleep(2000);
        CacheCargeInfo cacheCargeInfo = CacheCargeInfo.cacheCargeInfo(userNo, "testEpi", 100);
        cacheChargeService.cacheCharge(cacheCargeInfo);

        then:
        UserInfo info = userInfoService.findByUserNo(userNo);
        info.getCache() == 1000;
        userInfoService.deleteByUserNo(userNo);
    }


    def "캐쉬가 충분한 상태에서 이용권 충전"(){
        given:
        String userNo = uuidGeneration.getUUID();
        UserInfo userInfo = UserInfo.userInfo(userNo, "testUserId", "qwer1243", "reader", "010-1111-1111", "female","testUser@email.com",1000);
        userInfoService.saveUserInfo(userInfo);
        String novelId = uuidGeneration.getUUID();
        Novel novel = Novel.novel(novelId, "메밀꽃 필 무렵", "이효석", 0, "메밀꽃 필 무렵 입니다.", 0, "novelImgUrl", 200) ;
        novelService.saveNovel(novel);

        when:
        NovelTicketChargeInfo novelTicketChargeInfo = NovelTicketChargeInfo.novelTicketChargeInfo(userNo, novelId, 3);
        novelTicketsChargeService.chargeTicket(novelTicketChargeInfo);

        then:
        UserInfo info = userInfoService.findByUserNo(userNo);
        println(info.getCache());
        info.getCache() == 400;
    }

    def "이용권 충전시 캐쉬가 충분하지 않으면 에러"(){
        given:
        String userNo = uuidGeneration.getUUID();
        UserInfo userInfo = UserInfo.userInfo(userNo, "origin", "orginPW", "reader", "010-1111-1111", "female","origin@email.com",0);
        userInfoService.saveUserInfo(userInfo);
        String novelId = uuidGeneration.getUUID();
        Novel novel = Novel.novel(novelId, "어린왕자", "생텍쥐페리", 0, "어린왕자 입니다.", 0, "novelImgUrl", 100) ;
        novelService.saveNovel(novel);

        when:
        NovelTicketChargeInfo novelTicketChargeInfo = NovelTicketChargeInfo.novelTicketChargeInfo(userNo, novelId, 3);
        novelTicketsChargeService.chargeTicket(novelTicketChargeInfo);

        then:
        def e = thrown(CommonException.class);
        println(e);
    }

}
