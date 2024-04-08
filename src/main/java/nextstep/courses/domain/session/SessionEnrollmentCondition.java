package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;

public interface SessionEnrollmentCondition {

    void isSatisfied(Session session) throws SessionException;

}
