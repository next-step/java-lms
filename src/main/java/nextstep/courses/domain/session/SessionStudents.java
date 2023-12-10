package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

import java.util.List;

public class SessionStudents {

    private List<NsUser> students;

    public SessionStudents(List<NsUser> students) {
        this.students = students;
    }

    public void add(NsUser student) {
        this.students.add(student);
    }

    public int enrolledStudentsCount() {
        return this.students.size();
    }
}
