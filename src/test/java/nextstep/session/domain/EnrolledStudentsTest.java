package nextstep.session.domain;

import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.session.domain.session.SessionType;
import nextstep.session.domain.student.EnrolledStudents;
import nextstep.session.domain.student.EnrollmentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnrolledStudentsTest {
    @Test
    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다")
    void paidSession_enrollmentLimitExists() {
        PaidPaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy( 800_000L, 1);

        assertThrows(IllegalStateException.class, () -> {
            EnrolledStudents enrolledStudents = new EnrolledStudents();
            enrolledStudents.enrollWithPolicyCheck(paidPaymentPolicy, SANJIGI, SessionType.AUTO_APPROVAL);
            enrolledStudents.enrollWithPolicyCheck(paidPaymentPolicy, JAVAJIGI, SessionType.AUTO_APPROVAL);
        });
    }

    @Test
    @DisplayName("수강신청한 학생에 대해서 강사는 수강 승인을 할 수 있다.")
    void approveEnrolledStudent() {
        PaidPaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy( 800_000L, 10);

        EnrolledStudents enrolledStudents = new EnrolledStudents();
        enrolledStudents.enrollWithPolicyCheck(paidPaymentPolicy, SANJIGI, SessionType.SELECTIVE_APPROVAL);
        enrolledStudents.enrollWithPolicyCheck(paidPaymentPolicy, JAVAJIGI, SessionType.SELECTIVE_APPROVAL);

        enrolledStudents.approveEnrollment(SANJIGI.getId());

        assertThat(enrolledStudents.getStudents().get(0).getEnrollmentStatus()).isEqualTo(EnrollmentStatus.APPROVED);
        assertEquals(2, enrolledStudents.count());
    }

    @Test
    @DisplayName("수강신청한 학생에 대해서 강사는 수강 취소를 할 수 있다.")
    void rejectEnrolledStudent() {
        PaidPaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy( 800_000L, 10);

        EnrolledStudents enrolledStudents = new EnrolledStudents();
        enrolledStudents.enrollWithPolicyCheck(paidPaymentPolicy, SANJIGI, SessionType.SELECTIVE_APPROVAL);
        enrolledStudents.enrollWithPolicyCheck(paidPaymentPolicy, JAVAJIGI, SessionType.SELECTIVE_APPROVAL);

        enrolledStudents.rejectEnrollment(SANJIGI.getId());

        assertThat(enrolledStudents.getStudents().get(0).getEnrollmentStatus()).isEqualTo(EnrollmentStatus.REJECTED);
        assertEquals(2, enrolledStudents.count());
    }
}