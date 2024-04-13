package nextstep.payments.exception;

import nextstep.payments.domain.Payment;

import java.text.MessageFormat;

import static nextstep.payments.exception.PaymentExceptionMessage.PAYMENT_AMOUNT_EXISTS_EXCEPTION;

public class PaymentAmountExistException extends PaymentException {

    public PaymentAmountExistException(Payment payment) {
        super(PAYMENT_AMOUNT_EXISTS_EXCEPTION, MessageFormat.format("결제한 금액: {0}", payment.getAmount()));
    }

}
