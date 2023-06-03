package nextstep.courses.domain.registration;

public class SessionRegistrationBuilder {

    private SessionStatus sessionStatus;
    private Students students;


    private SessionRegistrationBuilder(){
    }

    public static SessionRegistrationBuilder aSessionRegistrationBuilder() {
        return new SessionRegistrationBuilder();
    }

    public SessionRegistrationBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }

    public SessionRegistrationBuilder withStudents(Students students) {
        this.students = students;
        return this;
    }


    public SessionRegistration build() {
        SessionRegistration sessionRegistration = SessionRegistration.of(sessionStatus, students);
        return sessionRegistration;
    }
}
