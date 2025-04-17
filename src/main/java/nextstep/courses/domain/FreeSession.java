package nextstep.courses.domain;

import java.time.LocalDate;

public class FreeSession extends Session {

    protected FreeSession(LocalDate startAt, LocalDate endAt, CoverImage coverImage) {
        super(startAt, endAt, coverImage);
    }
}
