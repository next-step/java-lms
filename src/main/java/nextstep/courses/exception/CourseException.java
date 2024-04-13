package nextstep.courses.exception;

import java.text.MessageFormat;

public class CourseException extends RuntimeException {

    public CourseException(CourseExceptionMessage exceptionMessage, String detailMessage) {
        super(MessageFormat.format("{0} ({1})", exceptionMessage.getMessage(), detailMessage));
    }

}
