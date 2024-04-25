package nextstep.courses.error.exception;

import java.text.MessageFormat;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class PaymentConditionNotMetException extends RuntimeException {

    public PaymentConditionNotMetException(Money tuitionFee, Payment payment) {
        super(MessageFormat.format("{0} 강의료: {1}, 결제금액: {2}",
            "결제 금액이 일치하지 않아 강의 등록에 실패하였습니다"
            , tuitionFee.getValue(), payment.getAmount()));
    }
}
