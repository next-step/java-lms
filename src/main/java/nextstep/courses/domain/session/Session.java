package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;

public class Session {

    private final SessionCapacity capacity;
    private final SessionCoverImage coverImage;
    private final Long fee;
    private final SessionStatus sessionStatus;
    private final EnrollmentConditions enrollmentConditions;

    public Session(SessionCapacity capacity, SessionCoverImage coverImage, Long fee, SessionStatus sessionStatus, EnrollmentConditions enrollmentConditions) {
        this.capacity = capacity;
        this.coverImage = coverImage;
        this.fee = fee;
        this.sessionStatus = sessionStatus;
        this.enrollmentConditions = enrollmentConditions;
    }

    public boolean hasCapacity() {
        return capacity.hasCapacity();
    }

    public SessionCapacity getCapacity() {
        return capacity;
    }

    public boolean matchFee(Long amount) {
        return fee.equals(amount);
    }

    public Long getFee() {
        return fee;
    }
}
