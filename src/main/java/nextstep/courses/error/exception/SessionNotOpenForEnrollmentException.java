package nextstep.courses.error.exception;

import java.text.MessageFormat;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;

public class SessionNotOpenForEnrollmentException extends RuntimeException {

    public SessionNotOpenForEnrollmentException(SessionState sessionState) {
        super(MessageFormat.format("{0} 현재 강의 상태: {1}",
            "강의는 모집중 상태에서만 등록 가능합니다"
            , sessionState.getRecruitmentState()));
    }
}
