package nextstep.courses.domain.session;

import nextstep.courses.domain.session.builder.SessionBuilder;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class EnrollmentApplyTest {

    private NsUser U1 = new NsUser(1L, "chanani", "password", "이찬한", "aa@aa.aa");
    private Session S1 = new SessionBuilder().build();

    @Test
    void 수강신청_가능(){
        EnrollmentApply enrollmentApply = new EnrollmentApply(
                new Enrollments(),
                new Payment(1L, 1L, 300_000L),
                S1.getSessionCore()
        );

        Enrollment enrollment = enrollmentApply.enroll(U1, 1L);

        assertThat(enrollment.getSessionId()).isEqualTo(1L);
    }
}
