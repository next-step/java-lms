package nextstep.payments.domain;

import javax.print.DocFlavor;
import java.time.LocalDateTime;

public class Payment {
    private String id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt;

    private final Status status;

    public Payment() {
        this.status = Status.READY;
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this(id, sessionId, nsUserId, amount, LocalDateTime.now(), Status.READY);
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount, LocalDateTime createdAt, Status status) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.status = status;
    }

    public boolean isStatus(Status status) {
        return this.status == status;
    }

    public enum Status {
        READY,
        SUCCESS,
        FAIL
    }

}
