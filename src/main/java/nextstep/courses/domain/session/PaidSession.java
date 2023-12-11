package nextstep.courses.domain.session;

import nextstep.courses.domain.coverimage.CoverImage;

import java.time.LocalDate;

public class PaidSession extends Session {

    public PaidSession(CoverImage image, LocalDate startDate, LocalDate endDate) {
        super(image, false, startDate, endDate);
    }
}
