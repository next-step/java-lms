package nextstep.payments.domain;

import nextstep.session.domain.Session;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Payment {
    private String id;
    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Session session;
    private NsUser nsUser;

    public Payment() {
    }

    public Payment(Long amount, Session session, NsUser nsUser) {
        this.amount = amount;
        this.session = session;
        this.nsUser = nsUser;
    }

    public Session getSession() {
        return session;
    }

    public Long getAmount() {
        return amount;
    }
}
