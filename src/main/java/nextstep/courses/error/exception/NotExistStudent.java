package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class NotExistStudent extends RuntimeException {

    public NotExistStudent(Long studentId) {
        super(MessageFormat.format("{0} 입력값: {1}", "존재하지 않는 학생입니다.",
            studentId));
    }
}
