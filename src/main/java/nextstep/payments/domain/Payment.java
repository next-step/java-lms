package nextstep.payments.domain;

import nextstep.courses.domain.Amount;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Payment {
    private Long id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private NsUser nsUserId;

    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt;

    public Payment() {
    }

    private Payment(Long id, Long sessionId, NsUser nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public static Payment ofFree(Long sessionId, NsUser nsUserId) {
        return new Payment(0L, sessionId, nsUserId, 0L);
    }

    public static Payment ofPaid(Long id, Long sessionId, NsUser nsUserId, Long amount) {
        return new Payment(id, sessionId, nsUserId, amount);
    }

    public NsUser findUser() {
        return nsUserId;
    }

    public boolean isNotSameAmount(Amount fee) {
        return fee.isNotSameByLong(amount);
    }
}
