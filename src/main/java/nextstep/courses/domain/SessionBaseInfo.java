package nextstep.courses.domain;


import java.time.LocalDate;

public class SessionBaseInfo {
    private final Period period;
    private final CoverImage coverImage;
    private SessionState state;

    protected SessionBaseInfo(LocalDate startAt, LocalDate endAt, CoverImage coverImage) {
        this.period = new Period(startAt, endAt);
        this.coverImage = coverImage;
        this.state = SessionState.PREPARING;
    }

    public void open() {
        if (state != SessionState.PREPARING) {
            throw new IllegalStateException("준비중 상태가 아닙니다.");
        }
        this.state = SessionState.RECRUITING;
    }

    public void close() {
        if (state != SessionState.RECRUITING) {
            throw new IllegalStateException("모집중 상태가 아닙니다.");
        }
        this.state = SessionState.CLOSED;
    }

    public SessionState getState() {
        return state;
    }
}
