package numble.webnovel.member.repository

import numble.webnovel.member.domain.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
class MemberRepositoryTest extends Specification{

    @Autowired
    MemberRepository memberRepository;

    def "Member 생성 정상 케이스"(){
        given:
        def nickname = "testNickname";
        def password = "testPassword";
        def role = "testRole";
        def email = "test@email.com";
        def ownCache = 0;

        Member inputMember = Member.builder().nickname(nickname)
                                    .password(password)
                                    .email(email)
                                    .role(role)
                                    .ownCache(ownCache).build();

        when:
        Member member = memberRepository.save(inputMember);

        then:
        member.getNickname() == nickname;
        member.getPassword() == password;
        member.getRole() == role;
        member.getEmail() == email;
        member.getOwnCache() == ownCache;
    }

    def "Member nickname null 인 경우 에러 발생"(){
        given:
        def nickname = null;
        def password = "testPassword";
        def role = "testRole";
        def email = "test@email.com";
        def ownCache = 0;

        Member inputMember = Member.builder().nickname(nickname)
                .password(password)
                .email(email)
                .role(role)
                .ownCache(ownCache).build();

        when:
        Member member = memberRepository.save(inputMember);

        then:
        thrown(DataIntegrityViolationException);
    }

    def "Member password null 인 경우 에러 발생"(){
        given:
        def nickname = "testNickname";
        def password = null;
        def role = "testRole";
        def email = "test@email.com";
        def ownCache = 0;

        Member inputMember = Member.builder().nickname(nickname)
                .password(password)
                .email(email)
                .role(role)
                .ownCache(ownCache).build();

        when:
        Member member = memberRepository.save(inputMember);

        then:
        thrown(DataIntegrityViolationException);
    }

    def "Member email null 인 경우 에러 발생"(){
        given:
        def nickname = "testNickname";
        def password = "testPassword";
        def role = "testRole";
        def email = null;
        def ownCache = 0;

        Member inputMember = Member.builder().nickname(nickname)
                .password(password)
                .email(email)
                .role(role)
                .ownCache(ownCache).build();

        when:
        Member member = memberRepository.save(inputMember);

        then:
        thrown(DataIntegrityViolationException);
    }

    def "Member role null 인 경우 에러 발생"(){
        given:
        def nickname = "testNickname";
        def password = "testPassword";
        def role = null;
        def email = "test@email.com";
        def ownCache = 0;

        Member inputMember = Member.builder().nickname(nickname)
                .password(password)
                .email(email)
                .role(role)
                .ownCache(ownCache).build();

        when:
        Member member = memberRepository.save(inputMember);

        then:
        thrown(DataIntegrityViolationException);
    }
}
