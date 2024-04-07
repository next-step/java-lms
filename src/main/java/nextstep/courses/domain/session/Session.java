package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public class Session {

    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    private final SessionCapacity capacity;
    private final CoverImage coverImage;
    private final Long fee;
    private final SessionStatus sessionStatus;
    private final EnrollmentConditions enrollmentConditions;

    public Session(@NonNull LocalDateTime startAt,
                   @NonNull LocalDateTime endAt,
                   @NonNull SessionCapacity capacity,
                   @NonNull CoverImage coverImage,
                   @NonNull Long fee,
                   @NonNull SessionStatus sessionStatus,
                   @NonNull EnrollmentConditions enrollmentConditions) {
        this.startAt = startAt;
        this.endAt = endAt;
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
