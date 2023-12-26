package nextstep.session.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnrollmentTest {

    private SessionPricing sessionPricing;
    private Participants participants;
    private Enrollment enrollment;
    private NsUser nsUser;
    private Payment payment;

    @BeforeEach
    void setUp() {
        nsUser = NsUserTest.JAVAJIGI;
        payment = new Payment("payId", 1L, 1L, 1000L);
    }

    @Test
    @DisplayName("무료 세션에 대한 수강 신청이 정상적으로 처리되어야 한다")
    void enrollFreeSessionTest() {
        sessionPricing = new SessionPricing(new SessionPrice());
        participants = new Participants();
        enrollment = new Enrollment(sessionPricing, participants);
        enrollment.enrollStudent(nsUser);
        assertThat(enrollment.getParticipants().getStudents().contains(nsUser)).isTrue();
    }

    @Test
    @DisplayName("무료 세션 수강 신청시 결제 정보가 있으면 예외가 발생해야 한다")
    void enrollFreeSessionException() {
        sessionPricing = new SessionPricing(new SessionPrice());
        participants = new Participants();
        enrollment = new Enrollment(sessionPricing, participants);
        assertThatIllegalArgumentException().isThrownBy(
            () -> enrollment.enrollStudent(nsUser, payment));
    }

    @Test
    @DisplayName("유료 세션에 대한 수강 신청이 정상적으로 처리되어야 한다")
    void enrollPaidSessionTest() {
        sessionPricing = new SessionPricing(new SessionPrice(1000), 10);
        participants = new Participants();
        enrollment = new Enrollment(sessionPricing, participants);
        enrollment.enrollStudent(nsUser, payment);
        assertThat(enrollment.getParticipants().getStudents().contains(nsUser)).isTrue();
    }

    @Test
    @DisplayName("유료 세션 수강 신청시 결제 정보가 없으면 예외가 발생해야 한다")
    void enrollPaidSessionException() {
        sessionPricing = new SessionPricing(new SessionPrice(1000), 10);
        participants = new Participants();
        enrollment = new Enrollment(sessionPricing, participants);
        assertThatIllegalArgumentException().isThrownBy(() -> enrollment.enrollStudent(nsUser));
    }

}
