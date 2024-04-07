package nextstep.courses.domain.session;

import nextstep.courses.domain.Course;
import nextstep.courses.exception.SessionException;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public class Session {

    private final Long id;
    private final Course course;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    private final SessionCapacity capacity;
    private final CoverImage coverImage;
    private final Long fee;
    private final SessionStatus sessionStatus;
    private final EnrollmentConditions enrollmentConditions;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(Course course, LocalDateTime startAt, LocalDateTime endAt, SessionCapacity capacity, CoverImage coverImage, Long fee, SessionStatus sessionStatus, EnrollmentConditions enrollmentConditions) {
        this(0L, course, startAt, endAt, capacity, coverImage, fee, sessionStatus, enrollmentConditions);
    }

    public Session(@NonNull Long id,
                   @NonNull Course course,
                   @NonNull LocalDateTime startAt,
                   @NonNull LocalDateTime endAt,
                   @NonNull SessionCapacity capacity,
                   @NonNull CoverImage coverImage,
                   @NonNull Long fee,
                   @NonNull SessionStatus sessionStatus,
                   @NonNull EnrollmentConditions enrollmentConditions) {
        this.id = id;
        this.course = course;
        this.startAt = startAt;
        this.endAt = endAt;
        this.capacity = capacity;
        this.coverImage = coverImage;
        this.fee = fee;
        this.sessionStatus = sessionStatus;
        this.enrollmentConditions = enrollmentConditions;
        this.createdAt = LocalDateTime.now();
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
