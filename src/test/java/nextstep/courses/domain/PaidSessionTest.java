package nextstep.courses.domain;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.courses.domain.session.PaidSession;
import nextstep.courses.domain.session.RegistrationState;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaidSessionTest {

    private PaidSession paidSession = PaidSession.feeOf(1L,"step4", 1L,
            EnrollmentStatus.RECRUITING, LocalDate.now(), LocalDate.now(),
            LocalDateTime.now(), LocalDateTime.now(),1, 10_000L);
    private Payment payment = Payment.paidOf("1A", paidSession.getId(), NsUserTest.JAVAJIGI.getId(), 10_000L);
    private NsUser student = NsUserTest.JAVAJIGI;

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다. ")
    void sessionStudentTest() throws CannotSignUpException {

        paidSession.signUp(new Student(student.getId(), paidSession.getId(), RegistrationState.PENDING));
        assertThrows(CannotSignUpException.class, () -> paidSession.signUp(new Student(student.getId(), paidSession.getId(), RegistrationState.PENDING)));
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void payCheckTest() {
        assertDoesNotThrow(() -> paidSession.signUp(new Student(student.getId(), paidSession.getId(), RegistrationState.PENDING)));
    }

}
