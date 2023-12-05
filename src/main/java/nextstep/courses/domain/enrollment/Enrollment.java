package nextstep.courses.domain.enrollment;

public class Enrollment {
    private Long id;
    private Long nsUserId;
    private Long sessionId;

    public Enrollment(final Long nsUserId, final Long sessionId) {
        this.nsUserId = nsUserId;
        this.sessionId = sessionId;
    }
}
