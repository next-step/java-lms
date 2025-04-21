package nextstep.courses.domain.session.enrollment;

import lombok.Getter;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;

import java.util.List;

@Getter
public class EnrollmentManager {
    private final List<NsUser> enrolledUsers;
    private final SessionStatus status;

    public EnrollmentManager(List<NsUser> enrolledUsers, SessionStatus status) {
        this.enrolledUsers = enrolledUsers;
        this.status = status;
    }

    public void enroll(NsUser user) {
        validateEnrollment(user);
        enrolledUsers.add(user);
    }

    private void validateEnrollment(NsUser user) {
        if (user == null) {
            throw new IllegalArgumentException("수강 신청할 사용자가 없습니다.");
        }
        if (!status.isRecruiting()) {
            throw new IllegalStateException("수강 신청이 불가능합니다.");
        }
        if (hasEnrolledUser(user)) {
            throw new IllegalStateException("이미 수강 신청한 사용자입니다.");
        }
    }

    private boolean hasEnrolledUser(NsUser user) {
        return enrolledUsers.contains(user);
    }
}
