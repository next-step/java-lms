package nextstep.payments.domain;

import nextstep.courses.domain.Session;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Payment {
    private String id;

    // 결제한 강의
    private Long sessionId;

    // 결제한 사용자
    private Long nsUserId;

    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public Payment(String id, Session session, NsUser nsUser, Long amount) {
        this(id, session.getId(), nsUser.getId(), amount);
    }


    public boolean isSameAmountWith(long amount) {
        return this.amount == amount;
    }

    public boolean isSameUser(NsUser user) {
        return user.matchUser(this.nsUserId);
    }
}