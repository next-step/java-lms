package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    private final LocalDate startAt;
    private final LocalDate endAt;
    private final CoverImage coverImage;

    public Session(LocalDate startAt, LocalDate endAt, CoverImage coverImage) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.coverImage = coverImage;
    }

    public Session(LocalDate startAt, LocalDate endAt) {
        this(startAt, endAt, new CoverImage());
    }

    public Session() {
        this(LocalDate.now(), LocalDate.now());
    }
}
