package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    @Test
    void 코스는_강의를_등록할_수_있다() throws PeriodException {
        Course course = new Course(1);
        Session session = new Session(new Period(LocalDate.now(), LocalDate.now().plusDays(1)), SessionStatus.OPEN, new Students(), new SessionType(PayType.FREE, 1000L, 10), new SessionImage());
        assertThat(course.register(session)).contains(session);
    }
}
