package nextstep.courses.domain;


import java.time.LocalDate;

public abstract class Session {
    private final Period period;
    private final CoverImage coverImage;
    private final SessionState state;

    protected Session(LocalDate startAt, LocalDate endAt, CoverImage coverImage) {
        this.period = new Period(startAt, endAt);
        this.coverImage = coverImage;
        this.state = SessionState.PREPARING;
    }
}
