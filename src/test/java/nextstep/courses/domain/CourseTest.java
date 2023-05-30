package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void addSession() {
        Course course = new Course("TDD", 1L);
        Session session = SessionBuilder.aSession()
            .withId(1L)
            .build();

        course.addSession(session);

        assertThat(course.getSessions()).containsExactly(session);
    }

    private static Session sessionA() {
        return new Session(1L, SessionPeriodTest.sessionPeriodMinMax, null, true, SessionStatus.Recruiting,
            30);
    }

    private static Session sessionB() {
        return new Session(2L, SessionPeriodTest.sessionPeriodMinMax, null, true, SessionStatus.Recruiting,
            30);
    }

    private static NsUser userA() {
        return new NsUser(1L, "A", "A", "A", "A@A.A");
    }


}
