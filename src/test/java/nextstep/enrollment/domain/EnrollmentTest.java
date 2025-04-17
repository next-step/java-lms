package nextstep.enrollment.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionStatus;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EnrollmentTest {
    @Test
    @DisplayName("결제 금액이 강의 수강료와 일치하면 수강 신청에 성공한다.")
    void 결제_금액_강의_금액_일치() {
        new Enrollment(new NsUser(), new Session(10_000L), new Payment("user_id", 0L, 0L, 10_000L));
    }

    @Test
    @DisplayName("결제 금액이 강의 수강료와 다르면 예외를 던진다.")
    void 결제_금액_강의_금액_불일치() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                        new Enrollment(new NsUser(), new Session(10_000L), new Payment("user_id", 0L, 0L, 20_000L)))
                .withMessage("결제 금액이 수강료와 일치하지 않습니다.");
    }

    @Test
    @DisplayName("강의 상태가 모집중이면 수강 신청이 가능하다.")
    void 모집중_강의_수강_신청() {
        new Enrollment(new NsUser(), new Session(SessionStatus.RECRUITING, 10_000L), new Payment("user_id", 0L, 0L, 10_000L));
    }

    @Test
    @DisplayName("강의 상태가 모집중이 아니면 수강 신청 시 예외를 던진다.")
    void 모집중_아닌_강의_수강_신청() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            new Enrollment(new NsUser(), new Session(SessionStatus.CLOSED, 10_000L), new Payment("user_id", 0L, 0L, 10_000L)))
                .withMessage("강의 상태가 모집중일 때만 수강 신청이 가능합니다.");
    }
}