package nextstep.courses.exception;

import nextstep.courses.domain.session.SessionCapacity;

import java.text.MessageFormat;

public class ExceedSessionCapacityException extends SessionConditionException {
    public ExceedSessionCapacityException(SessionCapacity capacity) {
        super(SessionExceptionMessage.CAPACITY_EXCEED,
                MessageFormat.format("최대 수강인원: {0}, 현재 수강인원: {1}", capacity.getMaxCapacity(), capacity.getCurrentCapacity()));
    }
}
