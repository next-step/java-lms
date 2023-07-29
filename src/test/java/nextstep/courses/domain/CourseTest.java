package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course();

        course.addSession(new Session(0L, "TDD", 3, true));
        course.addSession(new Session(1L, "DDD", 3, false));
    }

    @Test
    void 강의_모집() {
        SessionStatus sessionStatus = course.startRecruiting(0L);

        assertThat(sessionStatus).isEqualTo(SessionStatus.RECRUITING);
    }

    @Test
    public void 수강신청_완료() throws Exception {
        course.startRecruiting(0L);

        assertThatNoException().isThrownBy(() -> course.enrolment(NsUserTest.JAVAJIGI, 0L));
    }

    @Test
    void 강의_시작() {
        course.startRecruiting(0L);
        SessionStatus sessionStatus = course.startSession(0L);

        assertThat(sessionStatus).isEqualTo(SessionStatus.PROCEEDING);
    }

    @Test
    void 강의_종료() {
        course.startRecruiting(0L);
        course.startSession(0L);
        SessionStatus sessionStatus = course.endSession(0L);

        assertThat(sessionStatus).isEqualTo(SessionStatus.END);

    }
}
