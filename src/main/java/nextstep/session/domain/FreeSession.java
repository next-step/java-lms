package nextstep.session.domain;

import java.time.LocalDate;

public class FreeSession extends Session {
    public FreeSession(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        super(creatorId, startDate, endDate, sessionImage);
    }

    public static FreeSession create(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        return new FreeSession(creatorId, startDate, endDate, sessionImage);
    }
}
