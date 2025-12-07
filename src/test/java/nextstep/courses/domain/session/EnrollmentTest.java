package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentTest {
    @Test
    public void 모집중_상태일때_수강신청_가능() {
        Enrollment enrollment = new Enrollment(1L, SessionStatus.RECRUITING, new FreeSessionType(), Collections.emptyList());

        EnrolledStudent student = enrollment.enroll(1L, null);

        assertThat(student.getNsUserId()).isEqualTo(1L);
        assertThat(student.getSessionId()).isEqualTo(1L);
    }

    @Test
    public void 준비중_상태일때_수강신청_불가() {
        Enrollment enrollment = new Enrollment(SessionStatus.PREPARING, new FreeSessionType());

        assertThatThrownBy(() -> {
            enrollment.enroll(1L, null);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("모집중인 강의만 수강 신청할 수 있다");
    }

    @Test
    public void 유료_강의_결제금액_검증() {
        SessionType type = new PaidSessionType(10, 100_000L);
        Enrollment enrollment = new Enrollment(1L, SessionStatus.RECRUITING, type, Collections.emptyList());

        assertThatThrownBy(() -> enrollment.enroll(1L, new Payment("결제번호-1", 1L, 1L, 50_000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 금액이 수강료와 일치하지 않습니다");
    }

    @Test
    public void 유료강의의_최대인원을_초과하면_예외() {
        long fee = 100_000L;
        SessionType type = new PaidSessionType(2, fee);

        List<EnrolledStudent> currentStudent = List.of(
                new EnrolledStudent(1L, 1L),
                new EnrolledStudent(1L, 2L)
        );

        Enrollment enrollment = new Enrollment(1L, SessionStatus.RECRUITING, type, currentStudent);

        assertThatThrownBy(() -> {
            enrollment.enroll(3L, new Payment("결제번호-1", 1L, 3L, fee));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("최대 수강 인원을 초과");
    }

}
