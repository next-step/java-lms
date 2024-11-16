package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.Set;

public class EnrolledUsers {

    private final Set<NsUser> enrolledUsers;

    private EnrolledUsers(Set<NsUser> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public static EnrolledUsers of(Set<NsUser> enrolledUsers) {
        return new EnrolledUsers(enrolledUsers);
    }
}
