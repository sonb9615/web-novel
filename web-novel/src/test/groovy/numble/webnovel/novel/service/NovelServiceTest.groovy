package numble.webnovel.novel.service

import numble.webnovel.exception.WebNovelServiceException
import numble.webnovel.novel.domain.Novel
import numble.webnovel.novel.dto.NovelInfoResponseList
import numble.webnovel.novel.dto.NovelRegisterRequest
import numble.webnovel.novel.dto.NovelUpdateRequest
import numble.webnovel.novel.enums.SerialStatus
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
        request.setSerialStatusForTest(status)

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
        request.setSerialStatusForTest(status)

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
        request.setSerialStatusForTest(status)

        when:
        novelService.registerNovel(request)

        then:
        thrown(WebNovelServiceException)
    }

    def "소설 정보 업데이트 정상 케이스"(){
        given:
        def novelId = 1L
        def status = SerialStatus.SERIES
        def novelInfo = "수정된 소설 정보"
        def novelImg = "수정된 소설 이미지"

        NovelUpdateRequest request = new NovelUpdateRequest()
        request.setSerialStatusForTest(status.getStatusName())
        request.setNovelImgForTest(novelImg)
        request.setNovelInfoForTest(novelInfo)

        Novel novel = Novel.builder()
                    .novelId(novelId)
                    .novelInfo("novelInfo")
                    .novelImg("novelImg")
                    .serialStatus(SerialStatus.SCHEDULE)
                    .build()

        novelRepository.findById(novelId) >> Optional.of(novel)

        when:
        novelService.updateNovel(novel.getNovelId(), request)
        def newNovelOptional = novelRepository.findById(novelId)
        def newNovel = newNovelOptional.get()

        then:
        newNovel.getNovelInfo() == novelInfo
        newNovel.getNovelImg() == novelImg
        newNovel.getSerialStatus() == status
    }

    def "잘못된 연재 상태를 업데이트하는 경우 에러 발생"(){
        given:
        def novelId = 1L
        def status = "올바르지 않은 연재 상태"
        def novelInfo = "수정된 소설 정보"
        def novelImg = "수정된 소설 이미지"

        NovelUpdateRequest request = new NovelUpdateRequest()
        request.setSerialStatusForTest(status)
        request.setNovelImgForTest(novelImg)
        request.setNovelInfoForTest(novelInfo)

        Novel novel = Novel.builder()
                .novelId(novelId)
                .novelInfo("novelInfo")
                .novelImg("novelImg")
                .serialStatus(null)
                .build()

        novelRepository.findById(novelId) >> Optional.of(novel)

        when:
        novelService.updateNovel(novel.getNovelId(), request)

        then:
        thrown(WebNovelServiceException)
    }

    def "소설 삭제 정상 케이스"(){
        given:
        def novelId = 1L

        Novel novel = Novel.builder()
                        . novelId(novelId)
                        .build()

        novelRepository.findById(novelId) >> Optional.of(novel)

        when:
        novelService.deleteNovel(novelId)

        then:
        1* novelRepository.delete(_)
    }

    def "소설제목과 작가로 검색 정상 케이스"(){
        given:

        Novel novel_1 = Novel.builder()
                        .novelId(1L)
                        .title("test_1")
                        .author("author_1")
                        .build()

        Novel novel_2 = Novel.builder()
                .novelId(2L)
                .title("test_2")
                .author("author_2")
                .build()

        novelRepository.findById(1L) >> Optional.of(novel_1)
        novelRepository.findById(2L) >> Optional.of(novel_2)

        when:
        NovelInfoResponseList novelList = novelService.showNovelListBySearchWord("test")
        then:
        novelList.getNovelList().size() == 2
        println novelList.getNovelList().get(0).getTitle()
    }
}
