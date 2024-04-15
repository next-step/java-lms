package nextstep.courses.exception;

import java.text.MessageFormat;

import static nextstep.courses.exception.CourseExceptionMessage.CAPACITY_EXCEED;

public class SessionCapacityExceedException extends CourseException {

    public SessionCapacityExceedException(int capacity, int studentsSize) {
        super(CAPACITY_EXCEED, MessageFormat.format("최대 수강인원: {0}, 현재 수강인원: {1})", capacity, studentsSize));
    }

}
