package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;
import org.springframework.lang.NonNull;

public class Session {

    private final SessionCapacity capacity;
    private final CoverImage coverImage;
    private final Long fee;
    private final SessionStatus sessionStatus;
    private final EnrollmentConditions enrollmentConditions;

    public Session(@NonNull SessionCapacity capacity,
                   @NonNull CoverImage coverImage,
                   @NonNull Long fee,
                   @NonNull SessionStatus sessionStatus,
                   @NonNull EnrollmentConditions enrollmentConditions) {
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

    public Session enroll() throws SessionException {
        validateEnrollmentPossible();
        capacity.increaseCurrentCapacity();
        return this;
    }

    private void validateEnrollmentPossible() throws SessionException {
        enrollmentConditions.validateSatisfy(this);
        sessionStatus.validateCanEnrollment();
    }
}
