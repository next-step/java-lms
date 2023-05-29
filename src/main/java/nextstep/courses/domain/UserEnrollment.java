package nextstep.courses.domain;

import nextstep.courses.domain.enums.ApprovalStatus;
import nextstep.users.domain.User;

import java.util.Objects;

public class UserEnrollment {
    private final User user;
    private ApprovalStatus approvalStatus;

    public UserEnrollment(User user, ApprovalStatus approvalStatus) {
        this.user = user;
        this.approvalStatus = approvalStatus;
    }

    public User getUser() {
        return user;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void approved() {
        this.approvalStatus = ApprovalStatus.APPROVED;
    }

    public void disApproved() {
        this.approvalStatus = ApprovalStatus.CANCELED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEnrollment that = (UserEnrollment) o;
        return Objects.equals(user, that.user) && approvalStatus == that.approvalStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, approvalStatus);
    }
}
