package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SessionTest {

    static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);

    @Test
    void startDateMustBeBeforeEndDate() {
        assertThatThrownBy(() -> new Session(END_DATE, START_DATE)).isInstanceOf(
                IllegalArgumentException.class).hasMessageContaining("시작일이 종료일보다");
    }

    @Test
    void sessionStatusIsPreparingOnCreation() {
        Session session = new Session(START_DATE, END_DATE);
        assertThat(session.status()).isEqualTo(SessionStatus.PREPARING);
    }


}