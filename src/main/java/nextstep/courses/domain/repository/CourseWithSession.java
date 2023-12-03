package nextstep.courses.domain.repository;

public class CourseWithSession {
    private final Long id;
    private final Long courseId;
    private final Long sessionId;

    public CourseWithSession(Long id, Long courseId, Long sessionId) {
        this.id = id;
        this.courseId = courseId;
        this.sessionId = sessionId;
    }
}
