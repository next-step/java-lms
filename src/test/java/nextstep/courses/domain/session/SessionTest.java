package nextstep.courses.domain.session;

import static nextstep.courses.domain.session.EnrollmentCountTest.ENROLLMENT_COUNT;
import static nextstep.courses.domain.session.NameTest.NAME;
import static nextstep.courses.domain.session.ScheduleTest.SCHEDULE;
import static nextstep.courses.domain.session.SessionStatus.OPEN;
import static nextstep.courses.domain.session.SessionStatus.PREPARING;
import static nextstep.courses.domain.session.image.CoverImageTest.COVER_IMAGE;
import static nextstep.courses.domain.session.strategy.PaidSessionStrategyTest.PAID_SESSION_STRATEGY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.payments.domain.Payment;

public class SessionTest {

    @Test
    @DisplayName("새로운 강의를 생성한다.")
    void Session() {
        final Session newSession = new Session(
                NAME,
                OPEN,
                SCHEDULE,
                PAID_SESSION_STRATEGY,
                ENROLLMENT_COUNT
        );

        assertThat(newSession).isEqualTo(session());
    }

    @Test
    @DisplayName("특정 강의에 대한 수강 신청을 한다.")
    void Enroll_Session() {
        final Session session = session();

        session.enroll(new Payment(10000));

        assertThat(session.currentEnrollmentCount())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("강의 상태가 “모집중”이 아닐 때, 수강 신청하는 경우 예외를 던진다.")
    void Enroll_NotOpeningSession_Exception() {
        final Session session = new Session(
                NAME,
                PREPARING,
                SCHEDULE,
                PAID_SESSION_STRATEGY,
                ENROLLMENT_COUNT
        );

        assertThatIllegalStateException()
                .isThrownBy(() -> session.enroll(new Payment(0)));
    }

    @Test
    @DisplayName("유료 강의의 수강 인원 제한을 초과하는 경우 예외를 던진다.")
    void Enroll_ExceedEnrollmentLimit_Exception() {
        final Session session = new Session(
                NAME,
                OPEN,
                SCHEDULE,
                PAID_SESSION_STRATEGY,
                new EnrollmentCount(10)
        );

        assertThatIllegalStateException()
                .isThrownBy(() -> session.enroll(new Payment(10000)));
    }

    @Test
    @DisplayName("유료 강의의 결제 금액과 수강료가 일치하지 않는 경우 예외를 던진다.")
    void Enroll_NotEqualFeeAndPayment_Exception() {
        final Session session = session();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.enroll(new Payment(9000)));
    }

    public static Session session() {
        final Session session = new Session(
                NAME,
                OPEN,
                SCHEDULE,
                PAID_SESSION_STRATEGY,
                ENROLLMENT_COUNT
        );

        session.assignCoverImage(COVER_IMAGE);

        return session;
    }
}
