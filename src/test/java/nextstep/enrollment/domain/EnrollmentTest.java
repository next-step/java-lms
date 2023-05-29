package nextstep.enrollment.domain;

import nextstep.sessions.domain.SessionTest;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class EnrollmentTest {
    @DisplayName("Enrollment 객체가 잘 생성되는지 확인")
    @Test
    void Enrollment_객체가_정상적으로_생성되는지_확인() {
        assertThat(Enrollment.of(SessionTest.S1, NsUserTest.JAVAJIGI, LocalDateTime.now())).isInstanceOf(Enrollment.class);
    }
}
