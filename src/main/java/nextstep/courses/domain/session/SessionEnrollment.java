package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.Set;

public class SessionEnrollment {

    private final ProgressStatus progressStatus;
    private final RecruitmentStatus recruitmentStatus;
    private final EnrolledUsers enrolledUsers;

    private SessionEnrollment(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.enrolledUsers = EnrolledUsers.of(new HashSet<>());
    }

    private SessionEnrollment(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, EnrolledUsers enrolledUsers) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.enrolledUsers = enrolledUsers;
    }

    public static SessionEnrollment of(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        return new SessionEnrollment(progressStatus, recruitmentStatus);
    }

    public static SessionEnrollment of(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, EnrolledUsers enrolledUsers) {
        return new SessionEnrollment(progressStatus, recruitmentStatus, enrolledUsers);
    }

    public void enrollUser(NsUser user) {
        validateDuplicateEnrollment(user);

        enrolledUsers.add(user);
    }

    public void validateDuplicateEnrollment(NsUser nsUser) {
        if (isDuplicateEnrolledUser(nsUser)) {
            throw new IllegalStateException("중복된 수강신청입니다.");
        }
    }

    private boolean isDuplicateEnrolledUser(NsUser nsUser) {
        return enrolledUsers.contains(nsUser);
    }

    public void approveUser(NsUser nsUser) {
        findEnrolledUser(nsUser).approve();
    }

    public void rejectUser(NsUser nsUser) {
        findEnrolledUser(nsUser).reject();
    }

    private NsUser findEnrolledUser(NsUser nsUser) {
        return enrolledUsers.getEnrolledUsers().stream()
                .filter(user -> user.matchUser(nsUser))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("수강신청하지 않은 사용자입니다."));
    }

    public int size() {
        return enrolledUsers.size();
    }

    public boolean isEnrollmentFull(int maxEnrollments) {
        return enrolledUsers.size() >= maxEnrollments;
    }

    public Set<NsUser> getEnrolledUsers() {
        return enrolledUsers.getEnrolledUsers();
    }

    public boolean isNotInProgress() {
        return progressStatus.isNotInProgress();
    }

    public boolean isNotRecruiting() {
        return recruitmentStatus.isNotRecruiting();
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

}
