package nextstep.courses.domain.session.condition;

import nextstep.courses.exception.SessionException;

public interface SessionCondition {

    void satisfy() throws SessionException;

}
