package nextstep.courses.app;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SessionServiceTest {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionRepository sessionRepository;

    @Test
    @DisplayName("저장")
    void test01() {
        sessionService.save(aSession().build());

        Session findSession = sessionRepository.findById(1L);

        assertThat(findSession.getId()).isNotNull();
    }


    @Test
    @DisplayName("조회")
    void test02() {
        sessionRepository.save(aSession().build());

        Session findSession = sessionService.findById(1L);

        assertThat(findSession.getId()).isEqualTo(1L);
    }

}
