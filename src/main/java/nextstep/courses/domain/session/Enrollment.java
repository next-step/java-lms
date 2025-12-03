package nextstep.courses.domain.session;

import nextstep.courses.domain.session.type.FreeType;
import nextstep.courses.domain.session.type.SessionType;

public class Enrollment {
    private SessionState state;
    private SessionType type;

    public Enrollment() {
        this(SessionState.PREPARING, new FreeType());
    }

    public Enrollment(SessionType type) {
        this(SessionState.PREPARING, type);
    }

    public Enrollment(SessionState state, SessionType type) {
        this.state = state;
        this.type = type;
    }

    public void enroll(long payAmount) {
        validateState();
        this.type = type.enroll(payAmount);
    }

    public void open() {
        this.state = state.open();
    }

    public void close() {
        this.state = state.close();
    }

    private void validateState() {
        if (!state.canEnroll()) {
            throw new IllegalStateException("모집중인 강의만 수강신청이 가능합니다.");
        }
    }
}