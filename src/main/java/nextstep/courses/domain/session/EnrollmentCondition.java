package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;

public interface EnrollmentCondition {

    void isSatisfied(Session session) throws SessionException;

}
