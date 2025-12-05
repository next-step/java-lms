package nextstep.courses.domain.session;

import nextstep.courses.domain.session.builder.EnrollmentBuilder;
import nextstep.courses.domain.session.builder.SessionBuilder;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentsTest {

    @Test
    void 중복_수강신청시_예외발생() {
        Session session = new SessionBuilder()
                .withEnrollment(new EnrollmentBuilder().build())
                .withSessionStatus(SessionStatus.ACTIVE)
                .build();

        Enrollment newEnrollment = new EnrollmentBuilder().build();


        assertThatThrownBy(() -> session.addEnrollment(newEnrollment, new Payment(1L, 1L, 300_000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 신청한 강의입니다.");
    }

}