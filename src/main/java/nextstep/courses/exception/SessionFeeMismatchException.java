package nextstep.courses.exception;

import nextstep.courses.domain.enrollment.SessionFee;
import nextstep.payments.domain.Payment;

import java.text.MessageFormat;

public class SessionFeeMismatchException extends CourseException {
    public SessionFeeMismatchException(SessionFee fee, Payment payment) {
        super(CourseExceptionMessage.PAYMENT_MISMATCH,
                MessageFormat.format("수강료: {0}, 결제금액: {1}", fee.get(), payment.getAmount()));
    }
}
