package nextstep.courses.error.exception;

import java.text.MessageFormat;

public class LectureNameEmptyException extends RuntimeException {

    public LectureNameEmptyException(String lectureName) {
        super(MessageFormat.format("{0} 입력값: {1}", "강의 이름이 입력되지 않았습니다",
            lectureName));
    }
}
