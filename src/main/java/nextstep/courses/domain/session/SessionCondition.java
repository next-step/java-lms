package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;

public interface SessionCondition {

    void canEnroll(Session session) throws SessionException;

}
