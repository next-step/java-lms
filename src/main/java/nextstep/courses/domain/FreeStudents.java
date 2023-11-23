package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class FreeStudents {

    private final List<NsUser> students;

    public FreeStudents() {
        this(new ArrayList<>());
    }

    public FreeStudents(List<NsUser> students) {
        this.students = students;
    }

    public void add(NsUser student) {
        students.add(student);
    }
}
