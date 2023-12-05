package nextstep.courses.domain.repository;

public class CourseSession {
    private final Long id;
    private final Long courseId;
    private final Long sessionId;

    public CourseSession(Long id, Long courseId, Long sessionId) {
        this.id = id;
        this.courseId = courseId;
        this.sessionId = sessionId;
    }
}
