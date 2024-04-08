package nextstep.courses.exception;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.users.domain.NsUser;

import java.text.MessageFormat;
import java.util.List;

public class ExceedSessionCapacityException extends SessionException {
    public ExceedSessionCapacityException(SessionCapacity capacity) {
        super(SessionExceptionMessage.CAPACITY_EXCEED,
                MessageFormat.format("최대 수강인원: {0}, 현재 수강인원: {1}", capacity.getMaxCapacity(), capacity.getCurrentCapacity()));
    }

    public ExceedSessionCapacityException(int maxCapacity, List<NsUser> users) {
        super(SessionExceptionMessage.CAPACITY_EXCEED,
                MessageFormat.format("최대 수강인원: {0}, 현재 수강인원: {1}", maxCapacity, users.size()));
    }
}
