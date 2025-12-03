package nextstep.courses.domain.service;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.builder.SessionBuilder;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
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

        assertThat(saveSession.getEnrollments()).hasSize(2);
    }

    @Test
    void session_생성(){
        Session session = new SessionBuilder().build();
        int count = sessionService.save(session);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void 수강신청_진행(){
        Session session = sessionService.findById(2L);
        Enrollment enrollment = new Enrollment(300L, NsUserTest.JAVAJIGI, session.getId(), new Payment(2L, 1L, 300_000L));
        session.addEnrollment(enrollment);

        int count = sessionService.saveEnrollment(enrollment);
        assertThat(count).isEqualTo(1);
    }



}