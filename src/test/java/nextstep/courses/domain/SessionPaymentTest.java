package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.dto.SessionPaymentDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionPaymentTest {
    @Test
    @DisplayName("SessionPayment 생성")
    void create() {
        assertThat(new SessionPayment()).isInstanceOf(SessionPayment.class);
    }

    @Test
    @DisplayName("전용 DTO 모델을 반환함")
    void toDto() {
        assertThat(new SessionPayment().toDto()).isInstanceOf(SessionPaymentDTO.class);
    }
}
