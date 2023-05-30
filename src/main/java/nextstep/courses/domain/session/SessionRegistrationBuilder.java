package nextstep.courses.domain.session;

import nextstep.courses.domain.student.Students;
import nextstep.users.domain.NsUser;

public class SessionRegistrationBuilder {

    private SessionStatus status;
    private Students students;
    private int studentCapacity;

    private SessionRegistrationBuilder() {
    }

    private SessionRegistrationBuilder(SessionRegistrationBuilder copy) {
        this.status = copy.status;
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

    public SessionRegistrationBuilder withStudents(Students students) {
        this.students = students;
        return this;
    }

    public SessionRegistrationBuilder withStudent(NsUser student, Session session) {
        students.add(student, session);
        return this;
    }

    public SessionRegistrationBuilder withStudentCapacity(int studentCapacity) {
        this.studentCapacity = studentCapacity;
        return this;
    }

    public SessionRegistration build() {
        return new SessionRegistration(status, students, studentCapacity);
    }

}
