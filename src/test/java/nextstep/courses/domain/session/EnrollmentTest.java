package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EnrollmentTest {

    @Test
    void 수강신청_정상_생성() {
        Enrollment enrollment = new Enrollment(NsUserTest.JAVAJIGI, 1L, new Payment(1L, 1L, 300_000L));
        assertThat(enrollment).isNotNull();
    }

}