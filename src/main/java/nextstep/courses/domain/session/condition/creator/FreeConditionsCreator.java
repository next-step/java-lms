package nextstep.courses.domain.session.condition.creator;

import nextstep.courses.domain.session.condition.SessionEnrollmentCondition;
import nextstep.courses.domain.session.condition.SessionNoneCondition;

import java.util.List;

public class FreeConditionsCreator implements ConditionsCreator {
    @Override
    public List<SessionEnrollmentCondition> create() {
        return List.of(new SessionNoneCondition());
    }
}
