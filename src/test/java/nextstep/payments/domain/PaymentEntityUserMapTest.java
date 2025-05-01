package nextstep.payments.domain;

import nextstep.payments.entity.PaymentEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PaymentEntityUserMapTest {

    @DisplayName("PaymentEntityUserMap 인스턴스 생성")
    @Test
    public void testConstructor() {
        PaymentEntity paymentEntity = PaymentEntity.builder()
            .id(1L)
            .userId("3")
            .sessionId(5L)
            .amount(500_000L)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .build();

        assertDoesNotThrow(() -> new PaymentEntityUserMap(Map.of(paymentEntity, JAVAJIGI)));
    }
}
