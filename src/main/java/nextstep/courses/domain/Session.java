package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private final Long id;
    private final CoverImage coverImage;
    private final Period period;
    private final Status status;

    public Session(Long id, CoverImage coverImage, Status status, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.coverImage = coverImage;
        this.period = new Period(startDate, endDate);
        this.status = status;
    }
}
