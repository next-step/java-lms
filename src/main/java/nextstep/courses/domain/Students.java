package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<NsUser> values;

    public Students() {
        this.values = new ArrayList<>();
    }

    public void addStudent(NsUser nsUser) {
        values.add(nsUser);
    }

    public boolean isRegistrationFull(int maxStudents) {
        return values.size() == maxStudents;
    }
}
