package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class CourseTest {

    @Test
    void testRegisterSession() {
        Course course = new Course("course1", 1L);
        assertThat(course.registerSession(new Session(1,"session1",new SessionPeriod(SessionPeriodTest.DATE_230601, SessionPeriodTest.DATE_230630),ChargeType.FREE, null, new SessionStudents(10), 1L, LocalDateTime.now(), null))).isTrue();
    }
}