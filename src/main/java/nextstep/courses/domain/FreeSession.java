package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FreeSession {
    private final List<NsUser> students;

    private FreeSession(List<NsUser> students) {
        this.students = students;
    }

    public FreeSession(NsUser... students) {
        this(new ArrayList<>(List.of(students)));
    }

    public void register(NsUser student) {
        students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeSession that = (FreeSession) o;
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }
}
