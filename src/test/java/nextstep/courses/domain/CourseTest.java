package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class CourseTest {

    @DisplayName("한 과정(Course) 에 동일한 강의 (Session) 를 추가하면 예외가 발생한다.")
    @Test
    void test01() {
        Course course = new Course("TDD", 1L);
        Session session = new FreeSession(1, 1, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        assertThatThrownBy(() -> {
            course.addSession(1, session);
            course.addSession(1, session);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 동일한 강의가 있습니다.");
    }

    @DisplayName("서로 다른 기수라면 동일 강의를 추가할 수 있다.")
    @Test
    void test02() {
        Course course = new Course("TDD", 1L);
        Session session = new FreeSession(1, 1, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        assertThatCode(() -> {
            course.addSession(1, session);
            course.addSession(2, session);
        }).doesNotThrowAnyException();
    }

    @DisplayName("수강 신청한다.")
    @Test
    void test03() {
        Course course = new Course("TDD, 클린코드 with Java", 0L);
        Session session = new FreeSession(0L, 1, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        Payment payment = new Payment(0L, NsUserTest.JAVAJIGI.getId(), 0L);
        boolean result = course.register(NsUserTest.JAVAJIGI, session, payment);
        assertThat(result).isEqualTo(true);
    }
}
