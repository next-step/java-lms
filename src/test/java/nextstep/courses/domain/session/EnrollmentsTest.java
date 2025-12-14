package nextstep.courses.domain.session;

import nextstep.courses.domain.session.builder.EnrollmentBuilder;
import nextstep.courses.domain.session.builder.SessionBuilder;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentsTest {

    private static final Enrollment E1 = new EnrollmentBuilder().build();

    @Test
    void 중복_수강신청시_예외발생() {
        Session session = new SessionBuilder()
                .withEnrollment(new EnrollmentBuilder().build())
                .withSessionStatus(SessionStatus.ACTIVE)
                .build();

        EnrollmentApply enrollmentApply = new EnrollmentApply(new Enrollments(E1), new Payment(1L, 1L, 300_000L), session.getSessionCore());

        assertThatThrownBy(() -> enrollmentApply.enroll(E1.getUser(), session.getId()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 신청한 강의입니다.");
    }

}