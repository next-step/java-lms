package nextstep.courses.domain.session.condition;

import nextstep.courses.exception.SessionException;

public interface SessionEnrollmentCondition {

    void satisfy() throws SessionException;

}
