package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SessionEnrollment {

    private final SessionStatus sessionStatus;
    private final Set<NsUser> enrolledUsers;

    private SessionEnrollment(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        this.enrolledUsers = new HashSet<>();
    }

    private SessionEnrollment(SessionStatus sessionStatus, Set<NsUser> enrolledUsers) {
        this.sessionStatus = sessionStatus;
        this.enrolledUsers = enrolledUsers;
    }

    public static SessionEnrollment of(SessionStatus sessionStatus) {
        return new SessionEnrollment(sessionStatus);
    }

    public static SessionEnrollment of(SessionStatus sessionStatus, Set<NsUser> enrolledUsers) {
        return new SessionEnrollment(sessionStatus, enrolledUsers);
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

    public boolean isNotOpen() {
        return sessionStatus.isNotOpen();
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

}
