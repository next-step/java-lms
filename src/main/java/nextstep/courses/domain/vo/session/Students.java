package nextstep.courses.domain.vo.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Students {
    private final List<NsUser> students;

    public Students(NsUser... students) {
        this(List.of(students));
    }

    public Students(List<NsUser> students) {
        this.students = new ArrayList<>(students);
    }

    public void add(NsUser student) {
        this.students.add(student);
    }

    public int size() {
        return Collections.unmodifiableList(students).size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students1 = (Students) o;
        return Objects.equals(students, students1.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }
}
