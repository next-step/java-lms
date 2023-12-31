package nextstep.sessions.domain.Builder;

import nextstep.sessions.domain.SessionStudent;
import nextstep.sessions.domain.SessionStudents;

import java.util.HashSet;
import java.util.Set;

public class SessionStudentsBuilder {
    private Set<SessionStudent> students = new HashSet<>();

    public SessionStudentsBuilder withStudents(Set<SessionStudent> students) {
        this.students = students;
        return this;
    }

    public SessionStudents build() {
        students.add(new SessionStudentBuilder().build());
        return new SessionStudents(students);
    }
}
