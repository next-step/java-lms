package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaidSessionTest {
    @Test
    @DisplayName("PaidSession 생성")
    void testPaidSessionConstructor() {
        //given
        final String title = "TDD, 클린 코드 with Java";
        final double price = 3000;
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        //when
        PaidSession paidSession = new PaidSession(title, price, startDate, endDate);

        //then
        assertThat(paidSession.getTitle()).isEqualTo(title);
        assertThat(paidSession.getPrice()).isEqualTo(price);
        assertThat(paidSession.getStartDate()).isEqualTo(startDate);
        assertThat(paidSession.getEndDate()).isEqualTo(endDate);
    }
}
