package nextstep.courses.domain.session;

import nextstep.courses.domain.coverimage.CoverImage;

import java.time.LocalDate;

public class FreeSession extends Session {

    public FreeSession(CoverImage image, LocalDate startDate, LocalDate endDate) {
        super(image, true, startDate, endDate);
    }
}
