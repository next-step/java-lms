package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {

    private LocalDate startDate;
    private LocalDate endDate;
    private SessionCoverImage sessionCoverImage;
    private SessionStatus sessionStatus;
    private SessionStrategy sessionTypeStrategy;

    public Session(SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionStrategy sessionTypeStrategy) {
        this(null, null, sessionCoverImage, sessionStatus, sessionTypeStrategy);
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionStrategy sessionTypeStrategy) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionCoverImage = sessionCoverImage;
        this.sessionStatus = sessionStatus;
        this.sessionTypeStrategy = sessionTypeStrategy;
    }

    public boolean isValidCoverImage() {
        return sessionCoverImage.isValidCoverImage();
    }

    public boolean canEnroll() {
        return sessionTypeStrategy.canEnroll() && isRecruiting();
    }

    private boolean isRecruiting() {
        return sessionStatus.isRecruiting();
    }
}
