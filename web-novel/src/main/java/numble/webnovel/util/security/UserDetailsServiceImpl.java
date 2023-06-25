package numble.webnovel.util.security;

import lombok.RequiredArgsConstructor;
import numble.webnovel.exception.ErrorCode;
import numble.webnovel.exception.WebNovelServiceException;
import numble.webnovel.member.domain.Member;
import numble.webnovel.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static numble.webnovel.exception.ErrorCode.NO_EXISTS_MEMBER;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 유저 관련 값 전달
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByNickname(username)
                .orElseThrow(() -> new WebNovelServiceException(NO_EXISTS_MEMBER));

        return new UserDetailsImpl(member, username);
    }
}
