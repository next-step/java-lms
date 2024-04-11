package nextstep.payments.exception;

import java.text.MessageFormat;

public class PaymentException extends RuntimeException {

    public PaymentException(PaymentExceptionMessage exceptionMessage, String detailMessage) {
        super(MessageFormat.format("{0} ({1})", exceptionMessage.getMessage(), detailMessage));
    }

}
