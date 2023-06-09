package numble.webnovel.service

import numble.webnovel.domain.Novel
import numble.webnovel.repository.LibraryRepository
import numble.webnovel.repository.NovelRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootTest
@Rollback
@Transactional

class NovelServiceTest extends Specification{

    @Autowired
    private LibraryService userLibraryService;
    @Autowired
    private LibraryRepository userLibraryRepository;
    @Autowired
    private NovelService novelService;
    @Autowired
    private NovelRepository novelRepository;

    def "소설 찾기"(){
        given:
        String novelId = "45ed40d9224a4d70bc1fff6f8306881c";
        when:
        Novel novel = novelService.findNovel(novelId);
        then:
        "메밀꽃 필 무렵" == novel.getTitle();

    }

    def "좋아요수_증가_동시성_확인"(){
        given:
        Novel novel = Novel.createNovel("title","author", "info","img",100,"Action");
        novelRepository.save(novel);
        when:
        int threadCnt = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCnt);

        for(int i = 0; i < threadCnt; i++){
            executorService.submit({ ->
                try {
                    novel.plusLikeCnt();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        then:
        novel.getLikeCnt() == 100;
    }


//    def "유저 선호작 목록 불러오기"(){
//        given:
//
//        when:
//        String userId = "ef21dc73ec124a93b1d8896c08374de7";
//        List<UserLibrary> userLibraries = userLibraryService.findUserLibrary(userId);
//        then:
//        userLibraries.size() == 0;
//    }
//
//    def "별점 업데이트"(){
//        given:
//        String userEpiId = "user_lib_test_id_1";
//        when:
//        UserLibrary userLibrary = userLibraryRepository.findById(userEpiId).setStarPoint(3);
//        then:
//        UserLibrary userLibrary1 = userLibraryRepository.findById(userEpiId);
//        userLibrary1.getStarPoint() == 3;
//        println userLibrary1.getReadDt();
//    }


}
