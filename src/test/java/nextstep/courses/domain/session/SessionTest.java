package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    @Test
    @DisplayName("Session이 처음 생성되면, Session의 status는 Recruiting 상태가 아니다.")
    void testInitSessionStatusIsReady() {
        //given
        final Session freeSession = buildDefaultFreeSession();
        final Session paidSession = buildDefaultPaidSession();

        //when
        final boolean freeSessionIsNotRecruiting = freeSession.isNotRecruiting();
        final boolean paidSessionNotRecruiting = paidSession.isNotRecruiting();

        //then
        assertThat(freeSessionIsNotRecruiting).isTrue();
        assertThat(paidSessionNotRecruiting).isTrue();
    }

    private PaidSession buildDefaultPaidSession() {
        return new PaidSession(
                "TDD, 클린 코드 with Java",
                3000L,
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 0, 0)
        );
    }

    private FreeSession buildDefaultFreeSession() {
        return new FreeSession(
                "TDD, 클린 코드 with Java",
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 0, 0)
        );
    }
}
