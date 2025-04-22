package nextstep.courses.domain.session.metadata;

import java.time.LocalDate;

import nextstep.courses.domain.session.metadata.coverImage.CoverImage;

public class SessionMetadata {
    private final Period period;
    private final CoverImage coverImage;

    public SessionMetadata(Period period, CoverImage coverImage) {
        this.period = period;
        this.coverImage = coverImage;
    }

    public LocalDate startAt() {
        return period.startAt();
    }

    public LocalDate endAt() {
        return period.endAt();
    }
}
