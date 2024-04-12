package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EnrolledUsers {
    private final List<NsUser> enrolledUsers;

    public EnrolledUsers() {
        this(new ArrayList<>());
    }

    public EnrolledUsers(List<NsUser> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public int numberOfCurrentEnrollment() {
        return enrolledUsers.size();
    }

    public boolean isDuplicatedUser(NsUser user) {
        return new HashSet<>(enrolledUsers).contains(user);
    }

    public void add(NsUser user) {
        enrolledUsers.add(user);
    }
}
