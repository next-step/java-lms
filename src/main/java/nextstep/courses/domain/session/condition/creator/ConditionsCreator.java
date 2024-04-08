package nextstep.courses.domain.session.condition.creator;

import nextstep.courses.domain.session.condition.SessionEnrollmentCondition;

import java.util.List;

@FunctionalInterface
public interface ConditionsCreator {

    List<SessionEnrollmentCondition> create();

}
