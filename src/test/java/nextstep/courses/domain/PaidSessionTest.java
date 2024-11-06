package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;
import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.SessionStatus;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidSessionTest {
    PaidSession session;
    PaidSession sessionWithApplicant;

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        CoverImage coverImage = new CoverImage();
        Long price = 10_000L;
        SessionStatus status = SessionStatus.ACCEPTING;
        int limitOfApplicants = 10;

        session = new PaidSession(now, now, coverImage, price, status, limitOfApplicants, 0);
        sessionWithApplicant = new PaidSession(now, now, coverImage, price, status, limitOfApplicants, 1);
    }

    @Test
    void register_유료_강의를_등록할_수_있다() {
        session.register(new Payment(10000L));

        assertThat(session).isEqualTo(sessionWithApplicant);
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"ACCEPTING"})
    void register_모집중_상태가_아니면_강의를_등록할_수_없다(SessionStatus status) {
        assertThatThrownBy(() -> new PaidSession(10_000L, status).register(new Payment(10_000L))).isInstanceOf(CannotRegisterSessionException.class)
                .hasMessage("강의가 등록할 수 있는 상태가 아닙니다.");
    }

    @Test
    void register_지불한_금액이_강의료와_일치하지_않으면_등록할_수_없다() {
        assertThatThrownBy(() -> session.register(new Payment(20000L))).isInstanceOf(CannotRegisterSessionException.class)
                .hasMessage("결제 금액과 수강료가 일치하지 않습니다.");
    }
}
