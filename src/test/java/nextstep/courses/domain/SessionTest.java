package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;
import nextstep.courses.exception.SessionPriceException;
import nextstep.courses.exception.SessionUserCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static nextstep.courses.domain.SessionImageTest.*;
import static nextstep.courses.domain.SessionPriceTest.sessionPriceOneThousand;
import static nextstep.courses.domain.SessionPriceTest.sessionPriceTenThousand;
import static nextstep.courses.domain.SessionUserCountTest.maxSessionUserCount;
import static nextstep.courses.domain.SessionUserCountTest.zeroSessionUserCount;
import static nextstep.payments.domain.PaymentTest.paymentOneThousand;
import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    public static SessionPeriod normalSessionPeriod() {
        return new SessionPeriod(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 12, 31)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSessionStateExcludeOpen")
    @DisplayName("실패 - 수강 신청시 모집중이 아닌 경우 수강 신청을 할 수 없다.")
    void fail_session_register_not_open(SessionState sessionState) {
        Session session = new Session(
                normalSessionImage(),
                normalSessionPeriod(),
                sessionPriceOneThousand(),
                sessionState,
                SessionType.FREE,
                zeroSessionUserCount()
        );
        assertThatThrownBy(() -> session.register(paymentOneThousand()))
                .isInstanceOf(SessionException.class)
                .hasMessage("현재 강의가 모집중이지 않아 수강 신청을 할 수가 없습니다.");
    }

    private static Stream<Arguments> provideSessionStateExcludeOpen() {
        return Stream.of(
                Arguments.of(SessionState.PREPARE),
                Arguments.of(SessionState.CLOSE)
        );
    }

    @Test
    @DisplayName("성공 - 수강 신청시 모집중인 경우 수강 신청을 할 수 있다.")
    void success_session_register_open() {
        Session session = new Session(
                normalSessionImage(),
                normalSessionPeriod(),
                sessionPriceOneThousand(),
                SessionState.OPEN,
                SessionType.FREE,
                zeroSessionUserCount()
        );
        assertThatCode(() -> session.register(paymentOneThousand()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 유료 강의는 지정된 수강 인원 제한을 초과할 경우 신청할 수 없다.")
    void fail_paid_session_register_user_count_over() {
        Session session = new Session(
                normalSessionImage(),
                normalSessionPeriod(),
                sessionPriceOneThousand(),
                SessionState.OPEN,
                SessionType.PAID,
                maxSessionUserCount()
        );
        assertThatThrownBy(() -> session.register(paymentOneThousand()))
                .isInstanceOf(SessionUserCountException.class)
                .hasMessage("제한된 수강 신청 인원을 초과 하였습니다.");
    }

    @Test
    @DisplayName("성공 - 유료 강의는 지정된 수강 인원 제한을 초과하지 않았을 경우 경우 신청할 수 있다.")
    void success_paid_session_register_user_count_not_over() {
        Session session = new Session(
                normalSessionImage(),
                normalSessionPeriod(),
                sessionPriceOneThousand(),
                SessionState.OPEN,
                SessionType.PAID,
                zeroSessionUserCount()
        );

        assertThatCode(() -> session.register(paymentOneThousand()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 유료 강의는 강의 금액과 수강료가 일치하지 않으면 수강 신청을 할 수 없다.")
    void test() {
        Session session = new Session(
                normalSessionImage(),
                normalSessionPeriod(),
                sessionPriceTenThousand(),
                SessionState.OPEN,
                SessionType.PAID,
                zeroSessionUserCount()
        );
        assertThatThrownBy(() -> session.register(paymentOneThousand()))
                .isInstanceOf(SessionPriceException.class)
                .hasMessage("결제 금액과 강의 금액이 일치 하지 않습니다.");
    }

}
