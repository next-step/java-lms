package nextstep.courses.domain;

import nextstep.courses.exceptions.NotPeriodSessionException;
import nextstep.courses.exceptions.OverStudentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    void 세션_유효성_검사_수강신청_모집중() {
        int students = 1;
        Session session = SessionFixture
                .session(1, students, SessionStatus.RECRUIT, SessionType.PAID, "/", "test.jpg");

        session.enroll();

        Assertions.assertThat(session.getStudent()).isEqualTo(++students);
    }

    @Test
    void 세션_유효성_검사_수강신청_모집중_아님() {
        Session session = SessionFixture
                .session(1, 1, SessionStatus.QUIT, SessionType.PAID, "/", "test.jpg");

        Assertions.assertThatThrownBy(() -> session.enroll())
                .isInstanceOf(NotPeriodSessionException.class)
                .hasMessage("수강신청은 모집 중에만 가능합니다.");
    }

    @Test
    void 세션_유효성_검사_수강인원_초과() {
        int students = 31;
        Session session = SessionFixture
                .session(1, students, SessionStatus.RECRUIT, SessionType.PAID, "/", "test.jpg");

        Assertions.assertThatThrownBy(() -> session.enroll())
                .isInstanceOf(OverStudentException.class)
                .hasMessage("최대 수강인원을 초과할 수 없습니다.");
    }

}
