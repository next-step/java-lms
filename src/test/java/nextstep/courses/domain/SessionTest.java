package nextstep.courses.domain;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.Students;
import nextstep.courses.exceptions.NotPeriodSessionException;
import nextstep.courses.exceptions.OverStudentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SessionTest {

    private static Student student;

    @BeforeAll
    static void init() {
        student = new Student(1, "name");
    }

    @Test
    void 세션_유효성_검사_수강신청_모집중() {
        int students = 0;
        Session session = SessionFixture
                .session(4, students, SessionStatus.RECRUIT, SessionType.PAID);

        session.apply(student);

        Assertions.assertThat(session.currentStudentNumber()).isEqualTo(++students);
    }

    @Test
    void 세션_유효성_검사_수강신청_모집중_아님() {
        Session session = SessionFixture
                .session(1, 1, SessionStatus.QUIT, SessionType.PAID);

        Assertions.assertThatThrownBy(() -> session.apply(student))
                .isInstanceOf(NotPeriodSessionException.class)
                .hasMessage("수강신청은 모집 중에만 가능합니다.");
    }

    @Test
    void 세션_유효성_검사_수강인원_초과() {
        int students = 31;
        Session session = SessionFixture
                .session(1, students, SessionStatus.RECRUIT, SessionType.PAID);

        Assertions.assertThatThrownBy(() -> session.apply(student))
                .isInstanceOf(OverStudentException.class)
                .hasMessage("최대 수강인원을 초과할 수 없습니다.");
    }

}
