package nextstep.courses.domain.session.condition;

import nextstep.courses.domain.session.condition.creator.ConditionsCreator;
import nextstep.courses.exception.SessionException;

import java.util.List;

public class SessionEnrollmentConditions {

    private final List<SessionEnrollmentCondition> conditions;

    public SessionEnrollmentConditions(ConditionsCreator creator) {
        conditions = creator.create();
    }

    public void satisfy() throws SessionException {
        for (SessionEnrollmentCondition condition : conditions) {
            condition.satisfy();
        }
    }

}
