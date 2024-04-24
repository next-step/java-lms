package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class StudentNameEmptyException extends RuntimeException {

    public StudentNameEmptyException(String studentName) {
        super(MessageFormat.format("{0} 입력값: {1}", "학생 이름이 입력되지 않았습니다",
            studentName));
    }
}
