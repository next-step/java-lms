package nextstep.sessions.domain;

import nextstep.courses.domain.CourseTest;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    public static final Session s1 = new Session(
            1L, CourseTest.C1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            SessionPaymentType.FREE,
            SessionStatus.RECRUITING, 1);
    public static final Session s2 = new Session(
            2L, CourseTest.C1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            SessionPaymentType.FREE,
            SessionStatus.PREPARING, 1);
    public static final Session s3 = new Session(
            3L, CourseTest.C1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            SessionPaymentType.FREE,
            SessionStatus.PREPARING, 2);

    @BeforeAll
    static void setUp() {
        s1.enrollStudent(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName(value = "강의는 강의 최대 수강 인원을 초과할 수 없다")
    void test1() {
        assertThatThrownBy(() -> {
            s1.enrollStudent(NsUserTest.SANJIGI);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "수강신청")
    void test2() {
        assertThat(s1.getSessionStudents().getCurrentStudentCount()).isEqualTo(1);
    }

    @Test
    @DisplayName(value = "강의 수강신청은 강의 상태가 모집중일 때만 가능하다")
    void test3() {
        Session session = new Session(
                0L, CourseTest.C1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                SessionPaymentType.FREE,
                SessionStatus.RECRUITING, 1);

        session.enrollStudent(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> {
            session.enrollStudent(NsUserTest.JAVAJIGI);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName(value = "이미 수강신청한 학생이 중복 수강신청했을 경우")
    void test4() {
        assertThatThrownBy(() -> {
            s1.enrollStudent(NsUserTest.JAVAJIGI);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
