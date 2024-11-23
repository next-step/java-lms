package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class EnrolledUsers {

    private final Set<NsUser> enrolledUsers;

    private EnrolledUsers(Set<NsUser> enrolledUsers) {
        this.enrolledUsers = new HashSet<>(enrolledUsers);
    }

    public static EnrolledUsers of(Set<NsUser> enrolledUsers) {
        return new EnrolledUsers(enrolledUsers);
    }

    public Set<NsUser> getEnrolledUsers() {
        return Collections.unmodifiableSet(enrolledUsers);
    }

    public void add(NsUser user) {
        this.enrolledUsers.add(user);
    }

    public boolean contains(NsUser nsUser) {
        return enrolledUsers.contains(nsUser);
    }

    public int size() {
        return enrolledUsers.size();
    }
}
