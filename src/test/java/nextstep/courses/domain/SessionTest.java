package nextstep.courses.domain;

import nextstep.courses.domain.vo.ChargeTest;
import nextstep.courses.domain.vo.EnrollmentTest;
import nextstep.courses.domain.vo.SessionInfoTest;
import nextstep.courses.domain.vo.SessionPeriodTest;
import nextstep.courses.exception.AlreadyEnrolledException;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentTest;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    public static final Session FREE_SESSION = new Session(
            CourseTest.C1,
            SessionInfoTest.INFO,
            SessionPeriodTest.PERIOD,
            SessionImageTest.IMAGE,
            EnrollmentTest.ENROLL_0,
            ChargeTest.CHARGE_FREE,
            0L);

    public static final Session PAID_SESSION = new Session(
            CourseTest.C1,
            SessionInfoTest.INFO,
            SessionPeriodTest.PERIOD,
            SessionImageTest.IMAGE,
            EnrollmentTest.ENROLL_10,
            ChargeTest.CHARGE_PAID_100K,
            0L);

    @Test
    @DisplayName("강의 생성 실패 테스트 - ")
    void paidSessionFailForCapacityZeroTest() {
        assertThatThrownBy(() -> {
            new Session(
                    CourseTest.C1,
                    SessionInfoTest.INFO,
                    SessionPeriodTest.PERIOD,
                    SessionImageTest.IMAGE,
                    EnrollmentTest.ENROLL_0,
                    ChargeTest.CHARGE_PAID_100K,
                    0L);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 수강인원 제한이 있어야 합니다.");
    }

    @Test
    @DisplayName("강의 생성 실패 테스트 - ")
    void freeSessionFailForCapacityNotZeroTest() {
        assertThatThrownBy(() -> {
            new Session(
                    CourseTest.C1,
                    SessionInfoTest.INFO,
                    SessionPeriodTest.PERIOD,
                    SessionImageTest.IMAGE,
                    EnrollmentTest.ENROLL_10,
                    ChargeTest.CHARGE_FREE,
                    0L);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("무료 강의는 수강인원 제한이 없어야 합니다.");
    }

    @Test
    @DisplayName("수강신청 테스트")
    void enrollSessionTest() throws Exception {
        final Session freeSession = new Session(
                CourseTest.C1,
                SessionInfoTest.INFO,
                SessionPeriodTest.PERIOD,
                SessionImageTest.IMAGE,
                EnrollmentTest.ENROLL_0,
                ChargeTest.CHARGE_FREE,
                0L);

        final Session paidSession = new Session(
                CourseTest.C1,
                SessionInfoTest.INFO,
                SessionPeriodTest.PERIOD,
                SessionImageTest.IMAGE,
                EnrollmentTest.ENROLL_10,
                ChargeTest.CHARGE_PAID_100K,
                0L);

        paidSession.enroll(NsUserTest.SANJIGI, PaymentTest.PAYMENT_100K);

        freeSession.enroll(NsUserTest.SANJIGI, PaymentTest.PAYMENT_0);

        assertThat(paidSession.enrollment().students().count()).isEqualTo(2);
        assertThat(freeSession.enrollment().students().count()).isEqualTo(2);

    }

    @Test
    @DisplayName("수강신청 실패 테스트 - 가격 결제금액 불일치")
    void enrollSessionFailForPaymentAmountNotEqualsPriceTest() {
        final Session session = new Session(
                CourseTest.C1,
                SessionInfoTest.INFO,
                SessionPeriodTest.PERIOD,
                SessionImageTest.IMAGE,
                EnrollmentTest.ENROLL_0,
                ChargeTest.CHARGE_FREE,
                0L);

        assertThatThrownBy(() -> {
            session.enroll(NsUserTest.SANJIGI, PaymentTest.PAYMENT_100K);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의가격과 결제금액이 일치하지 않습니다.");


    }
}

