package nextstep.courses.exception;

import java.text.MessageFormat;

import static nextstep.courses.domain.session.SessionCapacity.MIN_CAPACITY;

public class InvalidSessionCapacityException extends SessionException {
    public InvalidSessionCapacityException(int capacity) {
        super(SessionExceptionMessage.CAPACITY_EXCEED,
                MessageFormat.format("최소 수강인원은 {0}명 입니다. (입력된 수강인원: {1})", MIN_CAPACITY, capacity));
    }

}
