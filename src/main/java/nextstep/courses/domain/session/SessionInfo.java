package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.SessionImage;

import java.time.LocalDate;

public class SessionInfo {
    private final int cohort;
    private final SessionPeriod period;
    private final SessionImage coverImage;

    public SessionInfo(int cohort, SessionPeriod period, SessionImage coverImage) {
        this.cohort = cohort;
        this.period = period;
        this.coverImage = coverImage;
    }

    public int getCohort() {
        return cohort;
    }

    public LocalDate getStartDate() {
        return period.getStartDate();
    }

    public LocalDate getEndDate() {
        return period.getEndDate();
    }

    public SessionImage getCoverImage() {
        return coverImage;
    }
}
