package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionConditionException;

public interface SessionCondition {

    void canEnroll(Session session) throws SessionConditionException;

}
