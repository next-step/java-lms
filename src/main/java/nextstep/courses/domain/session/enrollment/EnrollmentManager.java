package nextstep.courses.domain.session.enrollment;

import lombok.Getter;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnrollmentManager {
    private final Map<NsUser, EnrollmentStatus> enrollmentStatuses;
    @Getter
    private final SessionProgressStatus progressStatus;
    @Getter
    private final SessionRecruitmentStatus recruitmentStatus;

    public EnrollmentManager(SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus) {
        this.enrollmentStatuses = new HashMap<>();
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
    }

    public EnrollmentManager() {
        this(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
    }

    public void addEnrollment(NsUser user) {
        enrollmentStatuses.put(user, EnrollmentStatus.PENDING_APPROVAL);
    }

    public void approveEnrollment(NsUser user) {
        if (!enrollmentStatuses.containsKey(user)) {
            throw new IllegalArgumentException("해당 사용자의 수강신청 정보가 없습니다.");
        }

        EnrollmentStatus currentStatus = enrollmentStatuses.get(user);
        if (currentStatus != EnrollmentStatus.PENDING_APPROVAL) {
            throw new IllegalStateException("승인 대기 상태의 수강신청만 승인할 수 있습니다.");
        }

        enrollmentStatuses.put(user, EnrollmentStatus.ENROLLED);
    }

    public void cancelEnrollment(NsUser user) {
        if (!enrollmentStatuses.containsKey(user)) {
            throw new IllegalArgumentException("해당 사용자의 수강신청 정보가 없습니다.");
        }

        EnrollmentStatus currentStatus = enrollmentStatuses.get(user);
        if (currentStatus != EnrollmentStatus.PENDING_APPROVAL && currentStatus != EnrollmentStatus.ENROLLED) {
            throw new IllegalStateException("승인 대기 또는 수강신청 상태만 취소할 수 있습니다.");
        }

        enrollmentStatuses.put(user, EnrollmentStatus.CANCELLED);
    }

    public EnrollmentStatus getEnrollmentStatus(NsUser user) {
        return enrollmentStatuses.getOrDefault(user, null);
    }

    public List<NsUser> getEnrolledUsers() {
        return enrollmentStatuses.entrySet().stream()
                .filter(entry -> entry.getValue() == EnrollmentStatus.ENROLLED)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<NsUser> getPendingApprovalUsers() {
        return enrollmentStatuses.entrySet().stream()
                .filter(entry -> entry.getValue() == EnrollmentStatus.PENDING_APPROVAL)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<NsUser> getCancelledUsers() {
        return enrollmentStatuses.entrySet().stream()
                .filter(entry -> entry.getValue() == EnrollmentStatus.CANCELLED)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        for (Map.Entry<NsUser, EnrollmentStatus> entry : enrollmentStatuses.entrySet()) {
            enrollments.add(new Enrollment(entry.getKey(), entry.getValue()));
        }
        return enrollments;
    }

    public void enroll(NsUser user) {
        validateEnrollment(user);
        addEnrollment(user);
    }

    private void validateEnrollment(NsUser user) {
        if (user == null) {
            throw new IllegalArgumentException("수강 신청할 사용자가 없습니다.");
        }
        if (!recruitmentStatus.isRecruiting()) {
            throw new IllegalStateException("수강 신청이 불가능합니다.");
        }
        if (hasEnrolledUser(user)) {
            throw new IllegalStateException("이미 수강 신청한 사용자입니다.");
        }
    }

    private boolean hasEnrolledUser(NsUser user) {
        return enrollmentStatuses.containsKey(user);
    }

}
