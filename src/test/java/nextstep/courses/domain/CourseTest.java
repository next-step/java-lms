package nextstep.courses.domain;

import nextstep.courses.ImageException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
    @Test
    void 과정_생성() {
        Course course = new Course("TDD, 클린 코드 with Java", NsUserTest.JAVAJIGI.getId());
        assertThat(course.getTitle()).isEqualTo("TDD, 클린 코드 with Java");
    }

    @Test
    void 강의_추가() throws ImageException {
        Course course = new Course("TDD, 클린 코드 with Java", NsUserTest.JAVAJIGI.getId());
        Session session = new Session(1L, "자동차경주게임",
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 30),
                Image.createImage(500_000, 300, 200, "png"),
                ChargeType.CHARGE, 500_000,
                30, SessionStatus.ENROLLING);
        course.addSession(session);
        assertThat(course.sessionCount()).isEqualTo(1);
    }
}
