package nextstep.courses.domain;

import nextstep.courses.exception.BusinessInvalidValueException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionTest {

    @Test
    void 무료강의생성() {
        Session freeSession = Session.ofFree(LocalDateTime.now(), LocalDateTime.now().plusMonths(1), new SessionCover(300, 200, 1024, null));
        assertThat(freeSession.price()).isEqualTo(0L);
        assertThat(freeSession.capacity()).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    void 유료강의생성() {
        long sessionPrice = 1_000_000L;
        int sessionCapacity = 100;

        Session paidSession = Session.ofPaid(LocalDateTime.now(), LocalDateTime.now().plusMonths(1),
                sessionPrice, sessionCapacity, new SessionCover(300, 200, 1024, null));
        assertThat(paidSession.price()).isEqualTo(sessionPrice);
        assertThat(paidSession.capacity()).isEqualTo(sessionCapacity);
    }

    @Test
    void 최대수강인원_exception() {
        Session paidSession = Session.ofPaid(LocalDateTime.now(), LocalDateTime.now().plusMonths(1),
                1_000_000L, 0, new SessionCover(300, 200, 1024, null));
        assertThatExceptionOfType(BusinessInvalidValueException.class)
                .isThrownBy(() -> paidSession.addParticipant("김혜수"))
                .withMessage("최대수강인원을 초과했습니다.");
    }
}