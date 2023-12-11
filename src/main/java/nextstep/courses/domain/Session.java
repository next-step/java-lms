package nextstep.courses.domain;

import nextstep.courses.domain.coverimage.CoverImage;

import java.time.LocalDate;

public class Session {
    private CoverImage coverImage;
    private LocalDate startDate;

    private LocalDate endDate;

    public Session() {
        this(null, LocalDate.now(), null);
    }

    public Session(LocalDate startDate, LocalDate endDate) {
        this(null, startDate, endDate);
    }

    public Session(CoverImage image, LocalDate startDate, LocalDate endDate) {
        this.coverImage = image;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
