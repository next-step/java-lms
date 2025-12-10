package nextstep.courses.domain.session;

import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.Registrations;

public class Enrollment {
    private final Long sessionId;
    private final SessionState state;
    private final SessionPolicy policy;
    private final Registrations registrations;

    public Enrollment(Long sessionId, SessionState state, SessionPolicy policy, Registrations registrations) {
        validateState(state);
        this.sessionId = sessionId;
        this.state = state;
        this.policy = policy;
        this.registrations = registrations;
    }

    public Registration enroll(long payAmount, long userId) {
        if (registrations.isAlreadyRegistered(userId)) {
            throw new IllegalArgumentException("이미 수강신청한 학생입니다.");
        }

        policy.validate(payAmount, registrations);

        return new Registration(sessionId, userId);
    }

    private void validateState(SessionState state) {
        if (!state.canEnroll()) {
            throw new IllegalStateException("모집중인 강의만 수강신청이 가능합니다.");
        }
    }
}