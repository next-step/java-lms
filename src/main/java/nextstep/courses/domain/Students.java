package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Students {

    private final List<NsUser> students;

    public Students() {
        students = new ArrayList<>();
    }

    public int countEnrollment() {
        return students.size();
    }

    public void addStudent(NsUser user) {
        students.add(user);
    }

    public List<NsUser> fetchStudents() {
        return Collections.unmodifiableList(students);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}
