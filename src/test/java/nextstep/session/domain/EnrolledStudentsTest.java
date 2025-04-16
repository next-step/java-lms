package nextstep.session.domain;

import nextstep.payments.domain.PaidPaymentPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.junit.jupiter.api.Assertions.*;

class EnrolledStudentsTest {
    @Test
    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다")
    void paidSession_enrollmentLimitExists() {
        PaidPaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy( 800_000L, 1);

        EnrolledStudents enrolledStudents = new EnrolledStudents();
        enrolledStudents.add(paidPaymentPolicy, SANJIGI);

        assertThrows(IllegalStateException.class, () -> {
            enrolledStudents.add(paidPaymentPolicy, JAVAJIGI);
        });
    }
}