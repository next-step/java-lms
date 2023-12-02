package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<NsUser> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public void addStudent(NsUser nsUser) {
        students.add(nsUser);
    }

    public boolean isRegistrationFull(int maxStudents) {
        return students.size() == maxStudents;
    }
}
