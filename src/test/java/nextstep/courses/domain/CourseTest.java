package nextstep.courses.domain;

import static nextstep.courses.domain.SessionTest.SESSION1;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class CourseTest {

    public static final Course course = new Course(1L, "모듬코스", NsUserTest.JAVAJIGI.getId(), LocalDateTime.now(), LocalDateTime.now());

    @Test
    void addSession() {
        course.addSession(SESSION1);
        assertThat(course.sessionsSize()).isEqualTo(1);
    }
}
