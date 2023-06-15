package numble.webnovel.novel.service

import numble.webnovel.exception.WebNovelServiceException
import numble.webnovel.novel.domain.Novel
import numble.webnovel.novel.dto.NovelRegisterRequest
import numble.webnovel.novel.repository.NovelRepository
import spock.lang.Specification

class NovelServiceTest extends Specification{

    def novelService
    def novelRepository

    def setup(){
        novelRepository = Mock(NovelRepository.class)
        novelService = new NovelService(novelRepository)
    }

    def "소설 등록 정상 케이스"(){
        given:
        def title = "testTitle"
        def author = "testAuthor"
        def novelInfo = "testNovelInfo"
        def novelImg = "testNovelImg"
        def episodeCost = 100
        def genre = "로맨스"
        def status = "연재예정"

        NovelRegisterRequest request = new NovelRegisterRequest()
        request.setTitleForTest(title)
        request.setAuthorForTest(author)
        request.setEpisodeCostForTest(episodeCost)
        request.setNovelInfoForTest(novelInfo)
        request.setNovelImgForTest(novelImg)
        request.setGenreNameForTest(genre)
        request.setStatus(status)

        Novel novel = request.toNovel()
        novelRepository.save(novel) >> novel

        when:
        novelService.registerNovel(request)

        then:
        1*novelRepository.save(_ as Novel)
    }

    def "잘못된 장르로 소설 등록 케이스"(){
        given:
        def title = "testTitle"
        def author = "testAuthor"
        def novelInfo = "testNovelInfo"
        def novelImg = "testNovelImg"
        def episodeCost = 100
        def genre = "잘못된 장르"
        def status = "연재예정"

        NovelRegisterRequest request = new NovelRegisterRequest()
        request.setTitleForTest(title)
        request.setAuthorForTest(author)
        request.setEpisodeCostForTest(episodeCost)
        request.setNovelInfoForTest(novelInfo)
        request.setNovelImgForTest(novelImg)
        request.setGenreNameForTest(genre)
        request.setStatus(status)

        when:
        novelService.registerNovel(request)

        then:
        thrown(WebNovelServiceException)
    }

    def "잘못된 상태로 소설 등록 케이스"(){
        given:
        def title = "testTitle"
        def author = "testAuthor"
        def novelInfo = "testNovelInfo"
        def novelImg = "testNovelImg"
        def episodeCost = 100
        def genre = "로맨스"
        def status = "잘못된 연재 상태"

        NovelRegisterRequest request = new NovelRegisterRequest()
        request.setTitleForTest(title)
        request.setAuthorForTest(author)
        request.setEpisodeCostForTest(episodeCost)
        request.setNovelInfoForTest(novelInfo)
        request.setNovelImgForTest(novelImg)
        request.setGenreNameForTest(genre)
        request.setStatus(status)

        when:
        novelService.registerNovel(request)

        then:
        thrown(WebNovelServiceException)
    }
}
