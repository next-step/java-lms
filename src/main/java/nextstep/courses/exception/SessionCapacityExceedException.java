package nextstep.courses.exception;

import java.text.MessageFormat;

import static nextstep.courses.domain.enrollment.SessionCapacity.MIN_CAPACITY;
import static nextstep.courses.exception.CourseExceptionMessage.*;

public class SessionCapacityExceedException extends CourseException {

    public SessionCapacityExceedException(int capacity) {
        super(CAPACITY_EXCEED,
                MessageFormat.format("최소 수강인원은 {0}명 입니다. (입력된 수강인원: {1})", MIN_CAPACITY, capacity));
    }

    public SessionCapacityExceedException(int capacity, int studentsSize) {
        super(CAPACITY_EXCEED,
                MessageFormat.format("최대 수강인원을 초과했습니다. (최대 수강인원: {0}, 입력된 수강인원: {1})", capacity, studentsSize));
    }

}
