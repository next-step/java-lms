package nextstep.courses.domain.session.condition;

import nextstep.courses.domain.session.condition.creator.ConditionsCreator;
import nextstep.courses.exception.SessionException;

import java.util.List;

public class SessionConditions {

    private final List<SessionCondition> conditions;

    public SessionConditions(ConditionsCreator creator) {
        conditions = creator.create();
    }

    public void satisfy() throws SessionException {
        for (SessionCondition condition : conditions) {
            condition.satisfy();
        }
    }

}
