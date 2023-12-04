package nextstep.payments.domain;

import nextstep.courses.exception.session.InvalidPaymentAmountException;

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
        this.amount = 1_000L;
    }

    public Payment(Long amount) {
        /**
         * @TODO 아래 내용은 요구사항에 따라 무시하고 진행
         * 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
         * 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.
         * => 결제 됬다고 생각하고 금액만 할당
         */
        this.amount = amount;
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public void complete(long amount) {
        if(this.amount != amount){
            throw new InvalidPaymentAmountException("강의료가 결제되지 않았습니다.");
        }
    }
}
