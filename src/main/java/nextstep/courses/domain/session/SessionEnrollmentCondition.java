package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;

public interface SessionEnrollmentCondition {

    void satisfy(Session session) throws SessionException;

}
