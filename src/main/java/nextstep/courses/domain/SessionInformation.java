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
}
