package nextstep.courses.error.exception;

import java.text.MessageFormat;
import nextstep.courses.domain.student.ApprovalState;

public class AlreadyApprovedCancelException extends RuntimeException {

    public AlreadyApprovedCancelException(ApprovalState approvalState) {
        super(MessageFormat.format("{0} 현재 승인 상태: {1}",
            "이미 수강신청이 취소된 학생입니다."
            , approvalState.getValue()));
    }
}
