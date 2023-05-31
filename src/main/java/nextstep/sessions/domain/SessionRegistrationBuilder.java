package nextstep.sessions.domain;

import nextstep.students.domain.Students;

public class SessionRegistrationBuilder {

    private SessionStatus status;
    private SessionRecruitmentStatus recruitmentStatus;
    private Students students;
    private int studentCapacity;

    private SessionRegistrationBuilder() {
    }

    private SessionRegistrationBuilder(SessionRegistrationBuilder copy) {
        this.status = copy.status;
        this.recruitmentStatus = copy.recruitmentStatus;
        this.students = copy.students;
        this.studentCapacity = copy.studentCapacity;
    }

    public static SessionRegistrationBuilder aRegistration() {
        return new SessionRegistrationBuilder();
    }

    public SessionRegistrationBuilder but() {
        return new SessionRegistrationBuilder(this);
    }

    public SessionRegistrationBuilder withStatus(SessionStatus status) {
        this.status = status;
        return this;
    }

    public SessionRegistrationBuilder withRecruitmentStatus(SessionRecruitmentStatus recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
        return this;
    }

    public SessionRegistrationBuilder withStudents(Students students) {
        this.students = students;
        return this;
    }

    public SessionRegistrationBuilder withStudent(String userId, Long sessionId) {
        students.add(userId, sessionId);
        return this;
    }

    public SessionRegistrationBuilder withStudentCapacity(int studentCapacity) {
        this.studentCapacity = studentCapacity;
        return this;
    }

    public SessionRegistration build() {
        return new SessionRegistration(status, recruitmentStatus, students, studentCapacity);
    }

}
