package nextstep.session.domain.teacher;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SessionTeachers {

    private Set<SessionTeacher> teachers;

    public SessionTeachers(List<SessionTeacher> teachers) {
        this.teachers = teachers.stream().collect(Collectors.toSet());
    }

    public boolean contains(SessionTeacher teacher) {
        return teachers.contains(teacher);
    }

    public int getCountOfTeachers() {
        return teachers.size();
    }
}
