package nextstep.session.domain;

import java.util.Objects;

public class Student {

    private final Long id;
    private final Long user_id;
    private final Long session_id;

    public Student(Long id, Long user_id, Long session_id) {
        this.id = id;
        this.user_id = user_id;
        this.session_id = session_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(user_id,
            student.user_id) && Objects.equals(session_id, student.session_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, session_id);
    }
}
