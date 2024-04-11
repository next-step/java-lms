package nextstep.courses.exception;

import java.text.MessageFormat;

import static nextstep.courses.domain.enrollment.SessionCapacity.MIN_CAPACITY;

public class SessionCapacityExceedException extends CourseException {
    public SessionCapacityExceedException(int capacity) {
        super(CourseExceptionMessage.CAPACITY_EXCEED,
                MessageFormat.format("최소 수강인원은 {0}명 입니다. (입력된 수강인원: {1})", MIN_CAPACITY, capacity));
    }

}
