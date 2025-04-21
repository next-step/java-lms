package nextstep.payments.domain;

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

    public Payment() {
    }

    public Payment(Builder builder) {
        this.id = builder.id;
        this.sessionId = builder.sessionId;
        this.nsUserId = builder.nsUserId;
        this.amount = builder.amount;
        this.createdAt = builder.createdAt;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public Long getAmount() {
        return amount;
    }

    public static class Builder {
        private String id;
        private Long sessionId;
        private Long nsUserId;
        private Long amount;
        private LocalDateTime createdAt;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder sessionId(Long sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder nsUserId(Long nsUserId) {
            this.nsUserId = nsUserId;
            return this;
        }

        public Builder amount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
