package nextstep.courses.exception;

import nextstep.courses.domain.enrollment.SessionFee;

import java.text.MessageFormat;

public class MismatchSessionFeeException extends SessionException {
    public MismatchSessionFeeException(SessionFee fee, Long paymentAmount) {
        super(SessionExceptionMessage.PAYMENT_MISMATCH,
                MessageFormat.format("수강료: {0}, 결제금액: {1}", fee.get(), paymentAmount));
    }
}
