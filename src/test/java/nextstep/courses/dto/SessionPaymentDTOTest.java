package nextstep.courses.dto;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.SessionPaymentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionPaymentDTOTest {
    @Test
    @DisplayName("SessionPaymentDTO 생성")
    void create() {
        assertThat(new SessionPaymentDTO(SessionPaymentType.PAID, 10000L)).isInstanceOf(SessionPaymentDTO.class);
    }

    @Test
    @DisplayName("결제 방식 타입 반환")
    void getTypeString() {
        assertThat(new SessionPaymentDTO(SessionPaymentType.PAID, 10000L).getTypeString())
                .hasToString(SessionPaymentType.PAID.name());
    }

    @Test
    @DisplayName("결제 금액 반환")
    void getAmount() {
        assertThat(new SessionPaymentDTO(SessionPaymentType.PAID, 10000L).getAmount()).isEqualTo(10000L);
    }
}
