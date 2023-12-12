package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NotFreeEnrollmentTest {
    Enrollment enrollment;
    NsUser student;

    @BeforeEach
    void setUp() {
        EnrollmentCondition notFreeEnrollment = new NotFreeEnrollment(300_000, 1);
        enrollment = new Enrollment(notFreeEnrollment);
        enrollment.changeState(SessionState.RECRUIT);
        student = new NsUser(1L, "id", "password", "name", "email");
    }

    @DisplayName("강습료와 지불한 돈이 맞지 않으면 오류")
    @Test
    void 수강료_안맞음() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> enrollment.enroll(student, 0))
                .withMessage("강의 가격과 지불하신 돈이 일치하지 않습니다.");
    }

    @DisplayName("강의 최대 명수를 초과하면 오류")
    @Test
    void 최대_명수_초과() {
        enrollment.enroll(student, 300_000);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> enrollment.enroll(student, 300_000))
                .withMessage("최대 수강 인원을 초과했습니다.");
    }
}
