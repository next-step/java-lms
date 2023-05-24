package nextstep.lms.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {

    private List<Session> sessions;

    public Course(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(sessions, course.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessions);
    }
}
