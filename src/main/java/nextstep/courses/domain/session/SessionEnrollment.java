package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SessionEnrollment {

    private final ProgressStatus progressStatus;
    private final RecruitmentStatus recruitmentStatus;
    private final Set<NsUser> enrolledUsers;

    private SessionEnrollment(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.enrolledUsers = new HashSet<>();
    }

    private SessionEnrollment(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, Set<NsUser> enrolledUsers) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.enrolledUsers = enrolledUsers;
    }

    public static SessionEnrollment of(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        return new SessionEnrollment(progressStatus, recruitmentStatus);
    }

    public static SessionEnrollment of(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, Set<NsUser> enrolledUsers) {
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

    public int size() {
        return enrolledUsers.size();
    }

    public boolean isEnrollmentFull(int maxEnrollments) {
        return enrolledUsers.size() >= maxEnrollments;
    }

    public Set<NsUser> getEnrolledUsers() {
        return Collections.unmodifiableSet(enrolledUsers);
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
