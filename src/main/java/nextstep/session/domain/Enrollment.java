package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private final List<NsUser> students = new ArrayList<>();

    public Enrollment() {
    }

    public Enrollment(List<NsUser> students) {
        this.students.addAll(students);
    }

    public void add(NsUser user) {
        students.add(user);
    }

    public int enrolledNumber() {
        return this.students.size();
    }
}
