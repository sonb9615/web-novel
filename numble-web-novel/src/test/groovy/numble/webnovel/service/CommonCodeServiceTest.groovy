package numble.webnovel.service

import numble.webnovel.domain.CacheCargeRequest
import numble.webnovel.domain.ChildCode
import numble.webnovel.domain.UserInfo
import numble.webnovel.exceptions.CommonException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
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
        UserInfo userInfo = UserInfo.userInfo(userNo, "origin", "orginPW", "reader", "010-1111-1111", "female","origin@email.com",0);
        userInfoService.saveUserInfo(userInfo);
        chargeValidationService.saveCharge(userNo, userNo);

        when:
        CacheCargeRequest cacheCargeInfo = CacheCargeRequest.cacheCargeInfo(userNo, "testEpi", 100);
        cacheChargeService.cacheCharge(cacheCargeInfo);

        then:
        def e = thrown(CommonException.class);
        println(e);
    }

    def "1초 이후에 충전은 허용한다."(){
        given:
        String userNo = uuidGeneration.getUUID();
        UserInfo userInfo = UserInfo.userInfo(userNo, "origin_1", "orginPW", "reader", "010-1111-1111", "female","origin@email.com",0);
        userInfoService.saveUserInfo(userInfo);
        chargeValidationService.saveCharge(userNo, userNo);

        when:
        Thread.sleep(2000);
        CacheCargeRequest cacheCargeInfo = CacheCargeRequest.cacheCargeInfo(userNo, "testEpi", 100);
        cacheChargeService.cacheCharge(cacheCargeInfo);

        then:
        UserInfo info = userInfoService.findByUserNo(userNo);
        info.getCache() == 1000;
        userInfoService.deleteByUserNo(userNo);
    }




}
