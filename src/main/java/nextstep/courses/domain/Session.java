package nextstep.courses.domain;

import java.time.LocalDate;

public abstract class Session {
    private final SessionBaseInfo sessionBaseInfo;

    protected Session(LocalDate startAt, LocalDate endAt, CoverImage coverImage) {
        this.sessionBaseInfo = new SessionBaseInfo(startAt, endAt, coverImage);
    }

    void open() {
        sessionBaseInfo.open();
    }

    void close() {
        sessionBaseInfo.close();
    }
}
