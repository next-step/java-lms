package nextstep.courses.domain;

import java.util.List;
import java.util.Objects;
import nextstep.courses.domain.session.Session;

public class Cohort {

    private final Long id;

    private final Course course;

    private final String title;

    private final List<Session> sessions;

    public Cohort(Long id, Course course, String title, List<Session> sessions) {
        this.id = id;
        this.course = course;
        this.title = title;
        this.sessions = sessions;
    }

    public static Cohort of(Long id, Course course, String title, List<Session> sessions) {
        return new Cohort(id, course, title, sessions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cohort cohort = (Cohort) o;
        return Objects.equals(id, cohort.id) && Objects.equals(course,
            cohort.course) && Objects.equals(title, cohort.title) && Objects.equals(
            sessions, cohort.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, title, sessions);
    }

    public Long id() {
        return id;
    }

    public Course course() {
        return course;
    }

    public String title() {
        return title;
    }

    public List<Session> sessions() {
        return sessions;
    }

    @Override
    public String toString() {
        return "Cohort{" +
            "id=" + id +
            ", course=" + course +
            ", title='" + title + '\'' +
            ", sessions=" + sessions +
            '}';
    }
}
