package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    @Test
    public void create() throws Exception {
        Image coverImage = new Image("");
        LocalDate startDate = LocalDate.of(2023, 05, 27);
        LocalDate endDate = LocalDate.of(2023, 06, 25);
        assertThat(
                new Session(coverImage, PaymentType.FREE, SessionState.PREPARING, startDate, endDate)
        ).isNotNull()
                .isInstanceOf(Session.class);
    }
}
