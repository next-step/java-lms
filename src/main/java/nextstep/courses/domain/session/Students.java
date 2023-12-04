package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Students {
    private final List<NsUser> students;

    private Students(List<NsUser> students) {
        this.students = students;
    }

    public static Students of(List<NsUser> students) {
        return new Students(students);
    }

    public void add(NsUser student) {
        students.add(student);
    }

    public int size() {
        return students.size();
    }


}
