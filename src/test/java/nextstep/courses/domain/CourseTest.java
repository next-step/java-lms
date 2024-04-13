package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CourseTest {

    public static final String TDD_클린코드_WITH_JAVA = "TDD, 클린코드 with Java";
    public static final Course C1 = new Course(0L, TDD_클린코드_WITH_JAVA, 1L, 0L);

    @DisplayName("과정(Course)에 강의(Session)를 추가한다.")
    @Test
    void test011() {
        Course course = new Course(0L, TDD_클린코드_WITH_JAVA, 1L, 0L);
        Session session = new FreeSession(0L, course.getId(), List.of(SessionCoverImageTest.CI), SessionType.FREE);
        course.addSession(session);
        assertThat(course.isIncludeSession(session)).isTrue();
    }

    @DisplayName("과정(Course)에 이미 추가된 강의(Session)를 또 추가하면 예외가 발생한다.")
    @Test
    void test01() {
        Course course = new Course(0L, TDD_클린코드_WITH_JAVA, 1L, 0L);
        Session session = new FreeSession(0L, course.getId(), List.of(SessionCoverImageTest.CI), SessionType.FREE);
        course.addSession(session);
        assertThatThrownBy(() -> course.addSession(session))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 동일한 강의가 있습니다.");
    }

    @DisplayName("수강 신청한다.")
    @Test
    void test03() {
        Course course = new Course(0L, TDD_클린코드_WITH_JAVA, 1L, 0L);
        Session session = new FreeSession(0L, course.getId(), List.of(SessionCoverImageTest.CI), SessionType.FREE);
        session.changeRecruitmentStatus(RecruitmentStatus.RECRUITING);
        course.addSession(session);
        Payment payment = new Payment(0L, NsUserTest.JAVAJIGI.getId(), 0L);
        assertThatCode(() -> course.register(NsUserTest.JAVAJIGI, session, payment))
                .doesNotThrowAnyException();
    }
}
