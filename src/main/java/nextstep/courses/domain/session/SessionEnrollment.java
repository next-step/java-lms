package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SessionEnrollment {

    private SessionStatus sessionStatus;
    private Set<NsUser> enrolledUsers;

    private SessionEnrollment(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        this.enrolledUsers = new HashSet<>();
    }

    public static SessionEnrollment of(SessionStatus sessionStatus) {
        return new SessionEnrollment(sessionStatus);
    }

    public void enrollUser(NsUser user) {
        enrolledUsers.add(user);
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
        return sessionStatus != SessionStatus.OPEN;
    }

    public boolean contains(NsUser nsUser) {
        return enrolledUsers.contains(nsUser);
    }
}
