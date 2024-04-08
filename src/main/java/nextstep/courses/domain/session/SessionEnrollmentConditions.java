package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;

import java.util.ArrayList;
import java.util.List;

public class SessionEnrollmentConditions {

    private final List<SessionEnrollmentCondition> conditions;

    public static SessionEnrollmentConditions of(List<SessionEnrollmentCondition> conditions) {
        return new SessionEnrollmentConditions(conditions);
    }

    public static SessionEnrollmentConditions from(SessionEnrollmentCondition condition) {
        return new SessionEnrollmentConditions(List.of(condition));
    }

    public SessionEnrollmentConditions() {
        this(new ArrayList<>());
    }

    private SessionEnrollmentConditions(List<SessionEnrollmentCondition> conditions) {
        this.conditions = conditions;
    }

    public void validateSatisfy(Session session) throws SessionException {
        for (SessionEnrollmentCondition condition : conditions) {
            condition.isSatisfied(session);
        }
    }

}
