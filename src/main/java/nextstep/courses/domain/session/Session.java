package nextstep.courses.domain.session;

import nextstep.courses.domain.coverimage.CoverImage;

import java.time.LocalDate;

public class Session {
    CoverImage coverImage;
    Boolean free;
    LocalDate startDate;

    LocalDate endDate;

    public Session() {
        this(null, null, LocalDate.now(), null);
    }

    public Session(LocalDate startDate, LocalDate endDate) {
        this(null, null, startDate, endDate);
    }

    public Session(CoverImage image, Boolean free, LocalDate startDate, LocalDate endDate) {
        this.coverImage = image;
        this.free = free;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
