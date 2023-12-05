package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class NsUsers {

    private final List<NsUser> users = new ArrayList<>();

    public boolean enroll(NsUser nsUser) {
        return users.add(nsUser);
    }

    public int size() {
        return users.size();
    }

    public boolean isFullEnrollment(int maximumEnrollmentCount) {
        return users.size() >= maximumEnrollmentCount;
    }

}
