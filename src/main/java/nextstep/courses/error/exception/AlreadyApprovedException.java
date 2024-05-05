package nextstep.courses.error.exception;

import java.text.MessageFormat;
import nextstep.courses.domain.student.ApprovalState;
import nextstep.courses.domain.student.StudentType;

public class AlreadyApprovedException extends RuntimeException {

    public AlreadyApprovedException(ApprovalState approvalState) {
        super(MessageFormat.format("{0} 현재 승인 상태: {1}",
            "이미 수강신청이 승인된 학생입니다."
            , approvalState.getValue()));
    }
}
