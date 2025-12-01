package nextstep.courses.domain.session;

import nextstep.courses.domain.session.builder.EnrollmentBuilder;
import nextstep.courses.domain.session.builder.SessionBuilder;
import nextstep.courses.domain.session.constant.SessionStatus;
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

        assertThatThrownBy(() -> session.addEnrollment(newEnrollment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 신청한 강의입니다.");
    }

}