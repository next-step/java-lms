package nextstep.courses.domain.session.condition.creator;

import nextstep.courses.domain.session.condition.SessionCondition;
import nextstep.courses.domain.session.condition.SessionNoneCondition;

import java.util.List;

public class FreeSessionConditionsCreator implements SessionConditionsCreator {
    @Override
    public List<SessionCondition> create() {
        return List.of(new SessionNoneCondition());
    }
}
