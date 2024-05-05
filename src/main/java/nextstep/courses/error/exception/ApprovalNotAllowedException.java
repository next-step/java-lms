package nextstep.courses.error.exception;

import java.text.MessageFormat;
import nextstep.courses.domain.student.StudentType;

public class ApprovalNotAllowedException extends RuntimeException {

    public ApprovalNotAllowedException(StudentType studentType) {
        super(MessageFormat.format("{0}, 현재 학생 유형: {1}",
            "승인은 학생 유형 타입이 우아한테크코스 또는 우아한테크캠프인 경우만 수강신청이 가능합니다"
            , studentType.getValue()));
    }
}
