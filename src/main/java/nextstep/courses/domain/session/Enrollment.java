package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constant.EnrollmentStatus;
import nextstep.courses.domain.session.constant.SelectionStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enrollment extends BaseEntity {
    private final NsUser user;
    private final Long sessionId;
    private SelectionStatus selectionStatus;
    private EnrollmentStatus enrollmentStatus;

    public Enrollment(NsUser user, Long sessionId) {
        this(user, sessionId, LocalDateTime.now(), null);
    }

    public Enrollment(NsUser user, Long sessionId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, user, sessionId, createdAt, updatedAt, SelectionStatus.PENDING, EnrollmentStatus.WAITING);
    }

    public Enrollment(Long id, NsUser user, Long sessionId, LocalDateTime createdAt, LocalDateTime updatedAt, String selectionStatus, String enrollmentStatus) {
        this(id, user, sessionId, createdAt, updatedAt, SelectionStatus.valueOf(selectionStatus.toUpperCase()), EnrollmentStatus.valueOf(enrollmentStatus.toUpperCase()));
    }

    public Enrollment(NsUser user, Long sessionId, LocalDateTime createdAt, LocalDateTime updatedAt, SelectionStatus selectionStatus, EnrollmentStatus enrollmentStatus) {
        this(0L, user, sessionId, createdAt, updatedAt, selectionStatus, enrollmentStatus);
    }

    public Enrollment(Long id, NsUser user, Long sessionId, LocalDateTime createdAt, LocalDateTime updatedAt, SelectionStatus selectionStatus, EnrollmentStatus enrollmentStatus) {
        super(id, createdAt, updatedAt);
        this.user = user;
        this.sessionId = sessionId;
        this.selectionStatus = selectionStatus;
        this.enrollmentStatus = enrollmentStatus;
    }

    public NsUser getUser() {
        return user;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void updateEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        validateEnrollmentStatus(enrollmentStatus);
        this.enrollmentStatus = enrollmentStatus;
    }

    private void validateEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        validateNotSeletedAndApproved(enrollmentStatus);
        validateSelectedAndCancelled(enrollmentStatus);
    }

    private void validateSelectedAndCancelled(EnrollmentStatus enrollmentStatus) {
        if (isSelectedAndCancelled(enrollmentStatus)) {
            throw new IllegalArgumentException("선발된 인원은 강의 취소가 불가능합니다.");
        }
    }

    private void validateNotSeletedAndApproved(EnrollmentStatus enrollmentStatus) {
        if (isNotSelectedAndApproved(enrollmentStatus)) {
            throw new IllegalArgumentException("미선발된 인원은 강의 승인이 불가능합니다.");
        }
    }

    private boolean isSelectedAndCancelled(EnrollmentStatus enrollmentStatus) {
        return this.selectionStatus.isSelected() && enrollmentStatus.isCancled();
    }

    private boolean isNotSelectedAndApproved(EnrollmentStatus enrollmentStatus) {
        return this.selectionStatus.isNotSelected() && enrollmentStatus.isApproved();
    }

    public SelectionStatus getSelectionStatus() {
        return selectionStatus;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(user.getId(), that.user.getId()) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, sessionId);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "  user=" + user +
                ", sessionId=" + sessionId +
                '}';
    }
}
