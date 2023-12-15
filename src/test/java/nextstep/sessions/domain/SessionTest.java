package nextstep.sessions.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionTest {

    Session freeSession;
    Session paidSession;

    @BeforeEach
    void setUp() {
        freeSession = new Session(1L, "test1", SessionStatus.RECRUITING, new FreeSessionType(),
                LocalDate.now().minusDays(1), LocalDate.now().plusDays(30));
        paidSession = new Session(2L, "test2", SessionStatus.PREPARING, new PaidSessionType(50_000L, 30),
                LocalDate.now().plusDays(10), LocalDate.now().plusDays(60));
    }

    @Test
    void 수강_가능_상태_확인_테스트() {
        assertThat(freeSession.isRecruitingStatus()).isTrue();
        assertThat(paidSession.isRecruitingStatus()).isFalse();
    }

    @Test
    void 무료_강의_수강_신청_테스트() {
        assertThat(freeSession.isPossibleToRegister(0L, 1)).isTrue();
    }

    @Test
    void 유료_강의_수강_신청_실패_수강료_불일치_테스트() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> paidSession.isPossibleToRegister(50_000L - 1, 10));
    }

    @Test
    void 유료_강의_수강_신청_실패_수강_인원_초과_테스트() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> paidSession.isPossibleToRegister(40_000L, 30));
    }
}
