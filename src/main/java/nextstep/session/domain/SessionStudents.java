package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionStudents {
    private final List<NsUser> students = new ArrayList<>();

    public SessionStudents() {
    }

    public SessionStudents(List<NsUser> students) {
        this.students.addAll(students);
    }

    public List<NsUser> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public void add(NsUser user) {
        students.add(user);
    }

    public int enrolledNumber() {
        return this.students.size();
    }
}
