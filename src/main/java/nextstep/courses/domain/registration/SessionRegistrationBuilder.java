package nextstep.courses.domain.registration;

public class SessionRegistrationBuilder {

    private SessionRecruitmentStatus sessionRecruitmentStatus;
    private Students students;


    private SessionRegistrationBuilder(){
    }

    public static SessionRegistrationBuilder aSessionRegistrationBuilder() {
        return new SessionRegistrationBuilder();
    }


    public SessionRegistrationBuilder withSessionRecruitmentStatus(SessionRecruitmentStatus recruitmentStatus) {
        this.sessionRecruitmentStatus = recruitmentStatus;
        return this;
    }

    public SessionRegistrationBuilder withStudents(Students students) {
        this.students = students;
        return this;
    }


    public SessionRegistration build() {
        SessionRegistration sessionRegistration = new SessionRegistration(sessionRecruitmentStatus, students);
        return sessionRegistration;
    }
}
