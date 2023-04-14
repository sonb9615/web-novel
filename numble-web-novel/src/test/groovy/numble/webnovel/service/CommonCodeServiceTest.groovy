package numble.webnovel.service

import numble.webnovel.domain.ChildCode
import numble.webnovel.domain.ParentCode
import numble.webnovel.exceptions.CommonException
import numble.webnovel.service.CommonCodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CommonCodeServiceTest extends Specification{

    @Autowired
    CommonCodeService commonCodeService;

    def "부모코드가 없으면 자식코드는 저장이 되지 않는다"() {

        given:

        when:
        ChildCode childCode = ChildCode.childCode("CHILD_CODE","test","child_code");
        commonCodeService.saveChildCode(childCode);

        then:
        def e = thrown(CommonException.class)
        println(e);
    }

}
