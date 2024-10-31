package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class Session {

    private SessionDate sessionDate;
    private SessionCoverImage sessionCoverImage;
    private SessionStatus sessionStatus;
    private SessionStrategy sessionStrategy;

    public Session(SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionStrategy sessionStrategy) {
        this(null, null, sessionCoverImage, sessionStatus, sessionStrategy);
    }

    public Session(LocalDate startDate, LocalDate endDate, SessionCoverImage sessionCoverImage, SessionStatus sessionStatus, SessionStrategy sessionStrategy) {
        this.sessionDate = new SessionDate(startDate, endDate);
        this.sessionCoverImage = sessionCoverImage;
        this.sessionStatus = sessionStatus;
        this.sessionStrategy = sessionStrategy;
    }

    public boolean isValidCoverImage() {
        return sessionCoverImage.isValidCoverImage();
    }

    public boolean canEnroll(Payment payment) {
        return sessionStrategy.canEnroll(payment) && isRecruiting();
    }

    private boolean isRecruiting() {
        return sessionStatus.isRecruiting();
    }
}
