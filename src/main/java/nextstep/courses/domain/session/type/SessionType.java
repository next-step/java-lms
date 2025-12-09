package nextstep.courses.domain.session.type;

import nextstep.courses.domain.session.EnrollmentCondition;

public interface SessionType {
    boolean canEnroll(EnrollmentCondition request);

    String toHumanReadableTypeName();
}
