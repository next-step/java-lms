package nextstep.courses.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Course {
    private final long id;
    private final List<Session> sessions;

    private Course(long id, List<Session> sessions) {
        if (id == 0L) {
            throw new IllegalArgumentException("유효하지 않는 아이디에요 :( [입력 값 : " + id + "]");
        }

        this.id = id;
        this.sessions = sessions;
    }

    public static Course createCourse() {
        return createCourse(new ArrayList<>());
    }


    public static Course createCourse(List<Session> sessions) {
        long id = SimpleIdGenerator.getAndIncrement(Course.class);
        return new Course(id, sessions);
    }

    public static Course of(long id, List<Session> sessions) {
        return new Course(id, sessions);
    }

    public Course addSession(Session session) {
        List<Session> sessionList = new ArrayList<>(sessions);
        sessionList.add(session);
        return of(this.id, sessionList);
    }

    public Course concat(List<Session> sessions) {
        List<Session> sessionList = new ArrayList<>(this.sessions);
        sessionList.addAll(sessions);
        return of(this.id, sessionList);
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

    public List<Session> getSessions() {
        return Collections.unmodifiableList(sessions);
    }
}
