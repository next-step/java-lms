package nextstep.courses.domain;

import java.util.Objects;

public class SessionProgress {

    public static final String READY = "준비중";
    public static final String RECRUIT = "모집중";
    public static final String CLOSEUP = "종료";
    public String state;

    public SessionProgress() {
        this(READY);
    }

    public SessionProgress(String state) {
        checkRightState(state);
        this.state = state;
    }

    private void checkRightState(String state) {
        if (state.equals(READY) || state.equals(RECRUIT) || state.equals(CLOSEUP)) {
            return;
        }
        throw new IllegalArgumentException("알맞지 않은 강의 상태입니다.");
    }

    public SessionProgress update(String state) {
        this.state = state;
        return this;
    }

    public boolean compareWithRecruit(SessionProgress progress) {
        return progress.equals(new SessionProgress(RECRUIT));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof SessionProgress))
            return false;
        SessionProgress process = (SessionProgress) object;
        return Objects.equals(state, process.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

}
