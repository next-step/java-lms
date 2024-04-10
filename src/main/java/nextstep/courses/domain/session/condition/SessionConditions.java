package nextstep.courses.domain.session.condition;

import nextstep.courses.domain.session.condition.creator.SessionConditionsCreator;

import java.util.List;

public class SessionConditions {

    private final List<SessionCondition> conditions;

    public SessionConditions(SessionConditionsCreator creator) {
        conditions = creator.create();
    }

    public void satisfy() {
        for (SessionCondition condition : conditions) {
            condition.satisfy();
        }
    }

}
