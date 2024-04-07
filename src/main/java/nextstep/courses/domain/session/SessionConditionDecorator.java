package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;

abstract class SessionConditionDecorator implements SessionCondition {

    private final SessionCondition sessionCondition;

    public SessionConditionDecorator(SessionCondition sessionCondition) {
        this.sessionCondition = sessionCondition;
    }

    public void canEnroll(Session session) throws SessionException {
        sessionCondition.canEnroll(session);
    }
}
