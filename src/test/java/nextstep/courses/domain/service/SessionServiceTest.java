package nextstep.courses.domain.service;

import nextstep.courses.domain.session.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @Test
    void session_정상_조회(){
        Session saveSession = sessionService.findById(1L);

        assertThat(saveSession.getMaxCapacity()).isEqualTo(100);
        assertThat(saveSession.getTuition()).isEqualTo(300_000L);

        assertThat(saveSession.getCoverImage().getWidth()).isEqualTo(300);
        assertThat(saveSession.getCoverImage().getHeight()).isEqualTo(200);

        assertThat(saveSession.getCourse().getTitle()).isEqualTo("TDD, 클린 코드 with Java");
        assertThat(saveSession.getCourse().getCreatorId()).isEqualTo(1L);
    }



}