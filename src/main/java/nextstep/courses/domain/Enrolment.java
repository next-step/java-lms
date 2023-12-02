package nextstep.courses.domain;

public class Enrolment {
    private final Session session;

    public Enrolment(Session session) {
        this.session = session;
    }

    public void register() {
        validateSessionStatus();
        session.addStudent();
    }

    private void validateSessionStatus() {
        if (!session.isSessionStatus().isSessionResult()) {
            throw new IllegalArgumentException();
        }
    }
}
