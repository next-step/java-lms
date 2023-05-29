package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionRegistrationBuilder {

    private SessionStatus status;
    private List<NsUser> students;
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

    public SessionRegistrationBuilder withStudents(List<NsUser> students) {
        this.students = students;
        return this;
    }

    public SessionRegistrationBuilder withStudent(NsUser student) {
        addStudent(student);
        return this;
    }

    private void addStudent(NsUser student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    public SessionRegistrationBuilder withStudentCapacity(int studentCapacity) {
        this.studentCapacity = studentCapacity;
        return this;
    }

    public SessionRegistration build() {
        return new SessionRegistration(status, students, studentCapacity);
    }

}
