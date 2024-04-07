package nextstep.courses.exception;

import java.text.MessageFormat;

public class MismatchSessionFeeException extends SessionException {
    public MismatchSessionFeeException(Long sessionFee, Long paymentAmount) {
        super(SessionExceptionMessage.PAYMENT_MISMATCH,
                MessageFormat.format("수강료: {0}, 결제금액: {1}", sessionFee, paymentAmount));
    }
}
