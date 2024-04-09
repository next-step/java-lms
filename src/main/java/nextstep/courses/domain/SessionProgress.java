package nextstep.courses.domain;

import java.util.Objects;

public class SessionProcess {

    private String state;

    public SessionProcess() {
        this("준비중");
    }

    public SessionProcess(String state) {
        checkRightState(state);
        this.state = state;
    }

    private void checkRightState(String state) {
        if (state.equals("준비중") || state.equals("모집중") || state.equals("종료")) {
            return;
        }
        throw new IllegalArgumentException("알맞지 않은 강의 상태입니다.");
    }

    public SessionProcess update(String state) {
        this.state = state;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof SessionProcess))
            return false;
        SessionProcess process = (SessionProcess) object;
        return Objects.equals(state, process.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
