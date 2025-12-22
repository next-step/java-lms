package nextstep.courses.domain.session;

import nextstep.courses.domain.image.SessionImage;
import nextstep.courses.domain.image.SessionImages;

import java.time.LocalDate;

public class SessionInfo {
    private final int cohort;
    private final SessionPeriod period;
    private final SessionImages images;

    public SessionInfo(int cohort, LocalDate startDate, LocalDate endDate, SessionImage image) {
        this(cohort, new SessionPeriod(startDate, endDate), new SessionImages(image));
    }
    public SessionInfo(int cohort, LocalDate startDate, LocalDate endDate, SessionImages images) {
        this(cohort, new SessionPeriod(startDate, endDate), images);
    }


    public SessionInfo(int cohort, SessionPeriod period, SessionImages images) {
        this.cohort = cohort;
        this.period = period;
        this.images = images;
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

    public SessionImage getImage() {
        return images.getFirstImage();
    }

    public SessionImages getImages() {
        return images;
    }
}
