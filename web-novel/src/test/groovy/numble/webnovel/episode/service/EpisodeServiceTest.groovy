package numble.webnovel.episode.service

import numble.webnovel.episode.domain.Episode
import numble.webnovel.episode.dto.EpisodeRegisterRequest
import numble.webnovel.episode.dto.EpisodeUpdateRequest
import numble.webnovel.episode.repository.EpisodeRepository
import numble.webnovel.exception.WebNovelServiceException
import numble.webnovel.novel.domain.Novel
import spock.lang.Specification

class EpisodeServiceTest extends Specification{

    def episodeService
    def episodeRepository

    def setup(){
        episodeRepository = Mock(EpisodeRepository.class)
        episodeService = new EpisodeService(episodeRepository)
    }

    def "에피소드 등록 정상 케이스"(){
        given:
        def episodeNo = 1
        def episodeTitle = "testEpisodeTitle"
        def content = "testContent"
        def totalPage = 100
        def capacity = 4

        EpisodeRegisterRequest request = new EpisodeRegisterRequest()
        request.setEpisodeNoForTest(episodeNo)
        request.setEpisodeTitleForTest(episodeTitle)
        request.setContentForTest(content)
        request.setTotalPageForTest(totalPage)
        request.setCapacityForTest(capacity)

        def novelId = 1L
        Novel novel = Novel.builder()
                        .novelId(novelId)
                        .build()

        Episode episode = request.toEpisode(novel)

        episodeRepository.save(episode) >> episode

        when:
        episodeService.registerEpisode(novel, request)

        then:
        1*episodeRepository.save(_ as Episode)
    }

    def "에피소드 업데이트 정상 케이스"(){
        given:
        def episodeId = 1L
        def content = "수정된 내용"
        def freeYn = true
        def totalPage = 100
        def capacity = 4

        EpisodeUpdateRequest request = new EpisodeUpdateRequest()
        request.setContentForTest(content)
        request.setFreeYnForTest(freeYn)
        request.setTotalPageForTest(totalPage)
        request.setCapacityForTest(capacity)

        Episode episode = Episode.builder()
                            .episodeId(episodeId)
                            .build()

        episodeRepository.findById(episodeId) >> Optional.of(episode)

        when:
        episodeService.updateEpisode(episodeId, request)
        def newEpisode = episodeRepository.findById(episodeId).get()

        then:
        newEpisode.getContent() == content
        newEpisode.isFreeYn() == freeYn
        newEpisode.getTotalPage() == totalPage
        newEpisode.getCapacity() == capacity
    }

    def "에피소드 없는 경우 업데이트 에러 발생"(){
        given:
        def episodeId = 1L
        def content = "수정된 내용"
        def freeYn = true
        def totalPage = 100
        def capacity = 4

        EpisodeUpdateRequest request = new EpisodeUpdateRequest()
        request.setContentForTest(content)
        request.setFreeYnForTest(freeYn)
        request.setTotalPageForTest(totalPage)
        request.setCapacityForTest(capacity)

        episodeRepository.findById(episodeId) >> Optional.empty()

        when:
        episodeService.updateEpisode(episodeId, request)

        then:
        thrown(WebNovelServiceException)
    }

    def "에피소드 삭제 정상 케이스"(){
        given:
        def episodeId = 1L

        Episode episode = Episode.builder()
                .episodeId(episodeId)
                .build()

        episodeRepository.findById(episodeId) >> Optional.of(episode)

        when:
        episodeService.deleteEpisode(episodeId)

        then:
        1*episodeRepository.delete(episode)
    }

    def "에피소드 없는 경우 삭제 에러 발생"(){
        given:
        def episodeId = 1L

        episodeRepository.findById(episodeId) >> Optional.empty()

        when:
        episodeService.deleteEpisode(episodeId)

        then:
        thrown(WebNovelServiceException)
    }
}
