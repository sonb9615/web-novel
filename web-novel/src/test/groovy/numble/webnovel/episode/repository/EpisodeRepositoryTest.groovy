package numble.webnovel.episode.repository

import numble.webnovel.episode.domain.Episode
import numble.webnovel.novel.domain.Novel
import numble.webnovel.novel.repository.NovelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
class EpisodeRepositoryTest extends Specification{

    @Autowired
    EpisodeRepository episodeRepository
    @Autowired
    NovelRepository novelRepository

    def "Episode 생성 정상 케이스"(){
        given:
        def episodeNo = 1
        def episodeTitle = "testEpisodeTitle"
        def content = "testContent"
        def totalPage = 100
        def capacity = 4

        def novelId = 1L
        Novel novel = Novel.builder()
                        .novelId(novelId)
                        .build()

        novelRepository.findById(novelId) >> Optional.of(novel)

        Episode inputEpisode = Episode.builder()
                .episodeNo(episodeNo)
                .episodeTitle(episodeTitle)
                .content(content)
                .totalPage(totalPage)
                .capacity(capacity)
                .novel(novel)
                .build()

        when:
        Episode episode = episodeRepository.save(inputEpisode)

        then:
        episode.episodeNo == episodeNo
        episode.episodeTitle == episodeTitle
        episode.content == content
        episode.totalPage == totalPage
        episode.capacity == capacity
    }

    def "episodeTitle 없는 경우 에러 발생"(){
        given:
        def episodeNo = 1
        def episodeTitle = null
        def content = "testContent"
        def totalPage = 100
        def capacity = 4

        def novelId = 1L
        Novel novel = Novel.builder()
                .novelId(novelId)
                .build()

        novelRepository.findById(novelId) >> Optional.of(novel)

        Episode inputEpisode = Episode.builder()
                .episodeNo(episodeNo)
                .episodeTitle(episodeTitle)
                .content(content)
                .totalPage(totalPage)
                .capacity(capacity)
                .novel(novel)
                .build()

        when:
        episodeRepository.save(inputEpisode)

        then:
        thrown(DataIntegrityViolationException)
    }

    def "content 없는 경우 에러 발생"(){
        given:
        def episodeNo = 1
        def episodeTitle = "testEpisodeTitle"
        def content = null
        def totalPage = 100
        def capacity = 4

        def novelId = 1L
        Novel novel = Novel.builder()
                .novelId(novelId)
                .build()

        novelRepository.findById(novelId) >> Optional.of(novel)

        Episode inputEpisode = Episode.builder()
                .episodeNo(episodeNo)
                .episodeTitle(episodeTitle)
                .content(content)
                .totalPage(totalPage)
                .capacity(capacity)
                .novel(novel)
                .build()

        when:
        episodeRepository.save(inputEpisode)

        then:
        thrown(DataIntegrityViolationException)
    }

    def "novel 없는 경우 에러 발생"(){
        given:
        def episodeNo = 1
        def episodeTitle = "testEpisodeTitle"
        def content = "testContent"
        def totalPage = 100
        def capacity = 4

        Novel novel = null

        Episode inputEpisode = Episode.builder()
                .episodeNo(episodeNo)
                .episodeTitle(episodeTitle)
                .content(content)
                .totalPage(totalPage)
                .capacity(capacity)
                .novel(novel)
                .build()

        when:
        episodeRepository.save(inputEpisode)

        then:
        thrown(DataIntegrityViolationException)
    }
}
