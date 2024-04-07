package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentConditions {

    private final List<EnrollmentCondition> conditions;

    public static EnrollmentConditions of(List<EnrollmentCondition> conditions) {
        return new EnrollmentConditions(conditions);
    }

    public static EnrollmentConditions from(EnrollmentCondition condition) {
        return new EnrollmentConditions(List.of(condition));
    }

    public EnrollmentConditions() {
        this(new ArrayList<>());
    }

    private EnrollmentConditions(List<EnrollmentCondition> conditions) {
        this.conditions = conditions;
    }

    public void validateSatisfy(Session session) throws SessionException {
        for (EnrollmentCondition condition : conditions) {
            condition.isSatisfied(session);
        }
    }

}
