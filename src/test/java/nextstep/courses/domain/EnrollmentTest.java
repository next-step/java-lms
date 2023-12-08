package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.courses.exception.SessionPriceException;
import nextstep.courses.exception.SessionUserCountException;
import nextstep.courses.exception.SessionUserException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static nextstep.courses.domain.SessionPriceTest.sessionPriceOneThousand;
import static nextstep.courses.domain.SessionPriceTest.sessionPriceTenThousand;
import static nextstep.courses.domain.SessionUserCountTest.maxSessionUserCount;
import static nextstep.courses.domain.SessionUserCountTest.zeroSessionUserCount;
import static nextstep.payments.domain.PaymentTest.paymentOneThousand;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {

    public static Enrollment zeroAndOneThousandSession(SessionStatus sessionStatus, SessionRecruitment sessionRecruitment, SessionType sessionType) {
        return new Enrollment(
                sessionPriceOneThousand(),
                sessionStatus,
                sessionRecruitment,
                sessionType,
                zeroSessionUserCount()
        );
    }

    public static Enrollment zeroAndTenThousandSession(SessionStatus sessionStatus, SessionRecruitment sessionRecruitment, SessionType sessionType) {
        return new Enrollment(
                sessionPriceTenThousand(),
                sessionStatus,
                sessionRecruitment,
                sessionType,
                zeroSessionUserCount()
        );
    }

    public static Enrollment maxAndOneThousandSession(SessionStatus sessionStatus, SessionRecruitment sessionRecruitment, SessionType sessionType) {
        return new Enrollment(
                sessionPriceOneThousand(),
                sessionStatus,
                sessionRecruitment,
                sessionType,
                maxSessionUserCount()
        );
    }

    public static Enrollment test(SessionStatus sessionStatus, SessionRecruitment sessionRecruitment, SessionType sessionType) {
        return new Enrollment(
                sessionPriceOneThousand(),
                sessionStatus,
                sessionRecruitment,
                sessionType,
                maxSessionUserCount()
        );
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARE", "PROGRESS", "COMPLETE"})
    @DisplayName("실패 - 수강 신청시 모집중이 아닌 경우 수강 신청을 할 수 없다.")
    void fail_session_register_not_open(SessionStatus sessionStatus) {
        Enrollment enrollment = zeroAndOneThousandSession(sessionStatus, SessionRecruitment.CLOSE, SessionType.FREE);
        assertThatThrownBy(() -> enrollment.enroll(1L, SelectionStatus.SELECTED, JAVAJIGI, paymentOneThousand()))
                .isInstanceOf(SessionException.class)
                .hasMessage("현재 강의가 모집중이지 않아 수강 신청을 할 수가 없습니다.");
    }

    @Test
    @DisplayName("실패 - 수강 신청시 모집중 이더라도 강의가 종료된 경우 수강 신청을 할 수 없다.")
    void fail_session_register_not_open() {
        Enrollment enrollment = zeroAndOneThousandSession(SessionStatus.COMPLETE, SessionRecruitment.OPEN, SessionType.FREE);
        assertThatThrownBy(() -> enrollment.enroll(1L, SelectionStatus.SELECTED, JAVAJIGI, paymentOneThousand()))
                .isInstanceOf(SessionException.class)
                .hasMessage("현재 강의가 종료되어 수강 신청을 할 수가 없습니다.");
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"PREPARE", "PROGRESS"})
    @DisplayName("성공 - 수강 신청시 모집중이고 강의 진행 상태가 준비중, 진행중 일경우 수강 신청을 할 수 있다.")
    void success_session_register_open(SessionStatus sessionStatus) {
        Enrollment enrollment = zeroAndOneThousandSession(sessionStatus, SessionRecruitment.OPEN, SessionType.FREE);
        assertThatCode(() -> enrollment.enroll(1L, SelectionStatus.SELECTED, JAVAJIGI, paymentOneThousand()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 유료 강의는 지정된 수강 인원 제한을 초과할 경우 신청할 수 없다.")
    void fail_paid_session_register_user_count_over() {
        Enrollment enrollment = maxAndOneThousandSession(SessionStatus.PREPARE, SessionRecruitment.OPEN, SessionType.PAID);
        assertThatThrownBy(() -> enrollment.enroll(1L, SelectionStatus.SELECTED, JAVAJIGI, paymentOneThousand()))
                .isInstanceOf(SessionUserCountException.class)
                .hasMessage("제한된 수강 신청 인원을 초과 하였습니다.");
    }

    @Test
    @DisplayName("성공 - 유료 강의는 지정된 수강 인원 제한을 초과하지 않았을 경우 경우 신청할 수 있다.")
    void success_paid_session_register_user_count_not_over() {
        Enrollment enrollment = zeroAndOneThousandSession(SessionStatus.PREPARE, SessionRecruitment.OPEN, SessionType.PAID);
        assertThatCode(() -> enrollment.enroll(1L, SelectionStatus.SELECTED, JAVAJIGI, paymentOneThousand()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 유료 강의는 강의 금액과 수강료가 일치하지 않으면 수강 신청을 할 수 없다.")
    void test() {
        Enrollment enrollment = zeroAndTenThousandSession(SessionStatus.PREPARE, SessionRecruitment.OPEN, SessionType.PAID);
        assertThatThrownBy(() -> enrollment.enroll(1L, SelectionStatus.SELECTED, JAVAJIGI, paymentOneThousand()))
                .isInstanceOf(SessionPriceException.class)
                .hasMessage("결제 금액과 강의 금액이 일치 하지 않습니다.");
    }

    @Test
    @DisplayName("성공 - 유저가 기존에 신청 하지 않은 강의일 경우 수강 신청을 할 수 있다.")
    void success_session_register_user() {
        Enrollment enrollment = zeroAndOneThousandSession(SessionStatus.PREPARE, SessionRecruitment.OPEN, SessionType.PAID);
        assertThatCode(() -> enrollment.enroll(1L, SelectionStatus.SELECTED, JAVAJIGI, paymentOneThousand()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 유저가 기존에 신청한 강의일 경우 중복으로 수강 신청을 할 수 없다.")
    void fail_session_register_user() {
        Enrollment enrollment = zeroAndOneThousandSession(SessionStatus.PREPARE, SessionRecruitment.OPEN, SessionType.PAID);

        final Payment payment = paymentOneThousand();
        enrollment.enroll(1L, SelectionStatus.SELECTED, JAVAJIGI, payment);

        assertThatThrownBy(() -> enrollment.enroll(1L, SelectionStatus.SELECTED, JAVAJIGI, payment))
                .isInstanceOf(SessionUserException.class)
                .hasMessage("강의를 이미 신청한 유저이므로 중복으로 신청할 수 없습니다.");
    }

}
