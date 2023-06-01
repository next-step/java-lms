package nextstep.courses.service;

import nextstep.courses.domain.SessionFixture;
import nextstep.courses.domain.session.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    SessionService sessionService;

    @Test
    void save() {
        Long courseId = 1L;
        int currentStudent = 5;
        int maximumStudent = 10;
        SessionParticipant participant = SessionFixture.participant(maximumStudent, currentStudent);
        SessionCondition condition = SessionFixture.condition(SessionStatus.RECRUIT, SessionType.PAID);

        int save = sessionService.save(participant, condition, SessionFixture.term(), courseId);
        Assertions.assertThat(save).isEqualTo(1);

        Session session = sessionService.findById(1L);
        Assertions.assertThat(session.getMaxStudentCount()).isEqualTo(maximumStudent);
    }

}
