package nextstep.courses.domain;

import nextstep.courses.CannotRegisterSessionException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FreeSessionTest {
    FreeSession session;
    FreeSession sessionWithApplicant;

    @BeforeEach
    void setUp() {
        LocalDateTime now = LocalDateTime.now();
        CoverImage coverImage = new CoverImage();
        SessionStatus status = SessionStatus.ACCEPTING;

        session = new FreeSession(now, now, coverImage, status, 0);
        sessionWithApplicant = new FreeSession(now, now, coverImage, status, 1);
    }

    @Test
    void register_무료_강의를_등록할_수_있다() {
        session.register(new Payment(0L));

        assertThat(session).isEqualTo(sessionWithApplicant);
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"ACCEPTING"})
    void register_모집중_상태가_아니면_강의를_등록할_수_없다(SessionStatus status) {
        assertThatThrownBy(() -> new FreeSession(status).register(new Payment(0L))).isInstanceOf(CannotRegisterSessionException.class)
                .hasMessage("강의가 등록할 수 있는 상태가 아닙니다.");
    }
}
