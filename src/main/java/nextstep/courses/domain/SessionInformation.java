package nextstep.courses.domain;

public class SessionInformation {

    private SessionStatus status;

    private SessionEnrollment enrollment;

    private SessionSchedule schedule;

    public SessionInformation() {
    }

    public SessionInformation(SessionStatus status, SessionEnrollment enrollment, SessionSchedule schedule) {
        this.status = status;
        this.enrollment = enrollment;
        this.schedule = schedule;
    }

    public void enroll(Long userId, Long sessionId) {
        if (!status.isRecruiting()) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
        enrollment.enroll(userId, sessionId);
    }
}
