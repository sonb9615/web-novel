package numble.webnovel.episode.service

import numble.webnovel.episode.domain.Episode
import numble.webnovel.episode.dto.EpisodeRegisterRequest
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
}
