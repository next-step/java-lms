package nextstep.courses.domain.session.condition.creator;

import nextstep.courses.domain.session.condition.SessionCondition;

import java.util.List;

@FunctionalInterface
public interface ConditionsCreator {

    List<SessionCondition> create();

}
