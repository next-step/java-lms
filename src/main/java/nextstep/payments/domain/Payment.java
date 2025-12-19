package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {
    private String id;

    // 결제한 강의 아이디
    private Long cohortId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(String id, Long cohortId, Long nsUserId, Long amount) {
        this.id = id;
        this.cohortId = cohortId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isPayedCohort(Long cohortId) {
        return this.cohortId.equals(cohortId);
    }
}
