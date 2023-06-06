package nextstep.courses.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Enrollment {
    public static final String RECRUITMENT_STATUS_MESSAGE = "모집중인 강의가 아닙니다.";
    public static final String VALIDATE_APPROVED_USER = "수강 승인된 사용자입니다.";
    public static final String VALIDATE_NOT_APPROVED_USER = "수강 승인되지 않은 사용자입니다.";

    private final SessionUsers sessionUsers;
    private final SessionStatus sessionStatus;
    private final Set<Long> approvedUsers = new HashSet<>();

    public Enrollment(int maximumUserCount, SessionStatus sessionStatus) {
        this(new SessionUsers(maximumUserCount), sessionStatus);
    }

    public Enrollment(SessionUsers sessionUsers, SessionStatus sessionStatus) {
        this.sessionUsers = sessionUsers;
        this.sessionStatus = sessionStatus;
    }

    public void enroll(SessionUser sessionUser) {
        validateStatus();
        sessionUsers.enroll(sessionUser);
    }

    public void validateStatus() {
        if (!this.sessionStatus.canEnroll()) {
            throw new IllegalArgumentException(RECRUITMENT_STATUS_MESSAGE);
        }
    }

    public void approve(SessionUser sessionUser) {
        if (!approvedUsers.contains(sessionUser.getUserId())) {
            throw new IllegalArgumentException(VALIDATE_NOT_APPROVED_USER);
        }
        sessionUser.approve();
    }

    public void reject(SessionUser sessionUser) {
        if (approvedUsers.contains(sessionUser.getUserId())) {
            throw new IllegalArgumentException(VALIDATE_APPROVED_USER);
        }
        sessionUser.reject();
    }

    public void addApprovedUser(Long userId) {
        this.approvedUsers.add(userId);
    }

    public void addApprovedUsers(List<Long> userIds) {
        this.approvedUsers.addAll(userIds);
    }

    public int enrollmentCount() {
        return this.sessionUsers.enrollmentCount();
    }

    public int maximumEnrollmentCount() {
        return this.sessionUsers.getMaximumUserCount();
    }

    public String getProgressStatus() {
        return this.sessionStatus.getProgressStatus();
    }

    public String getRecruitmentStatus() {
        return this.sessionStatus.getRecruitmentStatus();
    }
}
