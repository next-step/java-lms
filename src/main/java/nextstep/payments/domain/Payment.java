package nextstep.payments.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Payment {
    private Long id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private NsUser nsUser;

    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(Long id, Long sessionId, NsUser nsUser, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUser = nsUser;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public static Payment ofFree(Long sessionId, NsUser nsUser) {
        return new Payment(0L, sessionId, nsUser, 0L);
    }

    public static Payment ofPaid(Long id, Long sessionId, NsUser nsUser, Long amount) {
        return new Payment(id, sessionId, nsUser, amount);
    }

    public NsUser findPaidUser() {
        return this.nsUser;
    }

    public Long findAmount() {
        return this.amount;
    }
}
