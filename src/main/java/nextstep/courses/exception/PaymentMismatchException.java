package nextstep.courses.exception;

public class PaymentMismatchException extends RuntimeException {
    public PaymentMismatchException(Long fee) {
        super("결제 금액이 수강료와 일치하지 않습니다. 수강료 : " + fee);
    }
}
