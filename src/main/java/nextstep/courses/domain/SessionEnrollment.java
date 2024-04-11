package nextstep.courses.domain;

public class SessionEnrollment {

    private SessionType type;

    private Fee fee;

    private Students maxStudents;

    public SessionEnrollment() {
    }

    public SessionEnrollment(SessionType type, int fee, Integer maxStudents) {
        this.type = type;
        this.fee = new Fee(fee, type);
        this.maxStudents = new Students(maxStudents, type);
    }
}
