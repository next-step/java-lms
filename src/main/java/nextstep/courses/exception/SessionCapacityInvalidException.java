package nextstep.courses.exception;

import java.text.MessageFormat;

import static nextstep.courses.domain.enrollment.SessionCapacity.MIN_CAPACITY;
import static nextstep.courses.exception.CourseExceptionMessage.*;

public class SessionCapacityInvalidException extends CourseException {

    public SessionCapacityInvalidException(int capacity) {
        super(INVALID_CAPACITY, MessageFormat.format("최소 수강인원: {0}, 입력된 수강인원: {1}", MIN_CAPACITY, capacity));
    }

}
