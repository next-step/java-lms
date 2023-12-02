package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentInfoTest {

    @ParameterizedTest
    @EnumSource(value = SessionState.class, names = "RECRUITING", mode = EnumSource.Mode.EXCLUDE)
    void 강의_모집중_아님(SessionState sessionState) {
        EnrollmentInfo enrollmentInfo = new EnrollmentInfo(new SessionType(PaidType.PAID, 800000, 2), sessionState);

        assertThatThrownBy(() -> enrollmentInfo.validate(1, new Payment()))
            .isInstanceOf(SessionsException.class)
            .hasMessage("모집중이 아닌 강의입니다.");
    }
}
