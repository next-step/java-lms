package nextstep.session.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    @Test
    void 강의_생성() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();

        // expect
        assertThat(Session.create(1, 1L, today, today.plusDays(1), "imageURL", SessionType.FREE))
                .isInstanceOf(Session.class);
    }
}