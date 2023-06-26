package numble.webnovel.chargeCache.service

import numble.webnovel.chargeCache.dto.ChargeCacheRequest
import numble.webnovel.chargeCache.repository.ChargeCacheHisRepository
import numble.webnovel.member.domain.Member
import numble.webnovel.member.dto.MemberLoginRequest
import numble.webnovel.member.repository.MemberRepository
import org.redisson.api.RedissonClient
import spock.lang.Specification

class chargeCacheServiceTest extends Specification{

    def cacheChargeService
    def redissonClient
    def memberRepository
    def chargeCacheHisRepository

    def setup(){
        redissonClient = Mock(RedissonClient)
        memberRepository = Mock(MemberRepository.class)
        chargeCacheHisRepository = Mock(ChargeCacheHisRepository.class)
        cacheChargeService = new ChargeCacheService(redissonClient, memberRepository, chargeCacheHisRepository)
    }

    def "캐쉬 충전 정상 케이스"(){

        given:
        def cache = 100
        def cost = 1000

        def chargeCacheRequest = new ChargeCacheRequest()
        chargeCacheRequest.setCacheForTest(cache)
        chargeCacheRequest.setCostForTest(cost)

        def nickname = "testNickname"
        Member member = Member.builder()
                .memberId(1L)
                .nickname(nickname)
                .build()

        memberRepository.findByNickname(nickname) >> Optional.of(member)

        when:
        cacheChargeService.chargeCache(member, chargeCacheRequest)
        def currentMember = memberRepository.findById(1L).get()

        then:
        currentMember.getOwnCache() == 100

    }
}
