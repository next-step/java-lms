package nextstep.payments.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode
public class PaymentEntity {
    private Long id;

    private String userId;

    private Long sessionId;

    private Long amount;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean deleted;

    public String getId() {
        return id.toString();
    }
}
