package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.courses.exception.SessionPriceException;
import nextstep.courses.exception.SessionUserCountException;
import nextstep.courses.exception.SessionUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static nextstep.courses.domain.SessionImageTest.normalSessionImage;
import static nextstep.courses.domain.SessionPeriodTest.normalSessionPeriod;
import static nextstep.courses.domain.SessionPriceTest.sessionPriceOneThousand;
import static nextstep.courses.domain.SessionPriceTest.sessionPriceTenThousand;
import static nextstep.courses.domain.SessionUserCountTest.maxSessionUserCount;
import static nextstep.courses.domain.SessionUserCountTest.zeroSessionUserCount;
import static nextstep.payments.domain.PaymentTest.paymentOneThousand;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    public static Session zeroAndOneThousandSession(SessionState sessionState, SessionType sessionType) {
        return new Session(
                normalSessionImage(),
                normalSessionPeriod(),
                sessionPriceOneThousand(),
                sessionState,
                sessionType,
                zeroSessionUserCount()
        );
    }

    public static Session zeroAndTenThousandSession(SessionState sessionState, SessionType sessionType) {
        return new Session(
                normalSessionImage(),
                normalSessionPeriod(),
                sessionPriceTenThousand(),
                sessionState,
                sessionType,
                zeroSessionUserCount()
        );
    }

    public static Session maxAndOneThousandSession(SessionState sessionState, SessionType sessionType) {
        return new Session(
                normalSessionImage(),
                normalSessionPeriod(),
                sessionPriceOneThousand(),
                sessionState,
                sessionType,
                maxSessionUserCount()
        );
    }

    public static Session test(SessionState sessionState, SessionType sessionType) {
        return new Session(
                normalSessionImage(),
                normalSessionPeriod(),
                sessionPriceOneThousand(),
                sessionState,
                sessionType,
                maxSessionUserCount()
        );
    }

    @ParameterizedTest
    @EnumSource(value = SessionState.class, names = {"PREPARE", "CLOSE"})
    @DisplayName("실패 - 수강 신청시 모집중이 아닌 경우 수강 신청을 할 수 없다.")
    void fail_session_register_not_open(SessionState sessionState) {
        Session session = zeroAndOneThousandSession(sessionState, SessionType.FREE);
        assertThatThrownBy(() -> session.register(JAVAJIGI, paymentOneThousand()))
                .isInstanceOf(SessionException.class)
                .hasMessage("현재 강의가 모집중이지 않아 수강 신청을 할 수가 없습니다.");
    }

    @Test
    @DisplayName("성공 - 수강 신청시 모집중인 경우 수강 신청을 할 수 있다.")
    void success_session_register_open() {
        Session session = zeroAndOneThousandSession(SessionState.OPEN, SessionType.FREE);
        assertThatCode(() -> session.register(JAVAJIGI, paymentOneThousand()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 유료 강의는 지정된 수강 인원 제한을 초과할 경우 신청할 수 없다.")
    void fail_paid_session_register_user_count_over() {
        Session session = maxAndOneThousandSession(SessionState.OPEN, SessionType.PAID);
        assertThatThrownBy(() -> session.register(JAVAJIGI, paymentOneThousand()))
                .isInstanceOf(SessionUserCountException.class)
                .hasMessage("제한된 수강 신청 인원을 초과 하였습니다.");
    }

    @Test
    @DisplayName("성공 - 유료 강의는 지정된 수강 인원 제한을 초과하지 않았을 경우 경우 신청할 수 있다.")
    void success_paid_session_register_user_count_not_over() {
        Session session = zeroAndOneThousandSession(SessionState.OPEN, SessionType.PAID);
        assertThatCode(() -> session.register(JAVAJIGI, paymentOneThousand()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 유료 강의는 강의 금액과 수강료가 일치하지 않으면 수강 신청을 할 수 없다.")
    void test() {
        Session session = zeroAndTenThousandSession(SessionState.OPEN, SessionType.PAID);
        assertThatThrownBy(() -> session.register(JAVAJIGI, paymentOneThousand()))
                .isInstanceOf(SessionPriceException.class)
                .hasMessage("결제 금액과 강의 금액이 일치 하지 않습니다.");
    }

    @Test
    @DisplayName("성공 - 유저가 기존에 신청 하지 않은 강의일 경우 수강 신청을 할 수 있다.")
    void success_session_register_user() {
        Session session = zeroAndOneThousandSession(SessionState.OPEN, SessionType.PAID);
        assertThatCode(() -> session.register(JAVAJIGI, paymentOneThousand()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 유저가 기존에 신청한 강의일 경우 중복으로 수강 신청을 할 수 없다.")
    void fail_session_register_user() {
        Session session = zeroAndOneThousandSession(SessionState.OPEN, SessionType.PAID);
        assertThatThrownBy(() -> duplicateAddSessionRegister(session))
                .isInstanceOf(SessionUserException.class)
                .hasMessage("강의를 이미 신청한 유저이므로 중복으로 신청할 수 없습니다.");
    }

    private void duplicateAddSessionRegister(Session session ) {
        for (int i = 0; i < 2; i++) {
            session.register(JAVAJIGI, paymentOneThousand());
        }
    }

}
