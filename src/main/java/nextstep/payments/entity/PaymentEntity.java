package nextstep.payments.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import nextstep.payments.domain.PaymentStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode
public class PaymentEntity {
    private Long id;

    private Long userId;

    private Long sessionId;

    private Long amount;

    private PaymentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean deleted;

    public String getId() {
        return id.toString();
    }
}
