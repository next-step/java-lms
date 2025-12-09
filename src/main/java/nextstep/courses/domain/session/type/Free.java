package nextstep.courses.domain.session.type;

import nextstep.courses.domain.session.EnrollmentCondition;

public class Free implements SessionType {
    public Free() {
    }

    @Override
    public boolean canEnroll(EnrollmentCondition request) {
        return true;
    }

    @Override
    public String toHumanReadableTypeName() {
        return "무료";
    }
}
