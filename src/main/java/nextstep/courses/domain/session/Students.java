package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Students {
    private final List<NsUser> students;

    public Students(final List<NsUser> students) {
        this.students = students;
    }

    public List<NsUser> getStudents() {
        return students;
    }

    public int size() {
        return students.size();
    }

}
