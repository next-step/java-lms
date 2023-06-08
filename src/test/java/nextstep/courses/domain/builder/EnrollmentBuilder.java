package nextstep.courses.domain.builder;

import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionType;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Students;

public class EnrollmentBuilder {
    private SessionStatus status;
    private SessionType type;
    private Students students;

    public EnrollmentBuilder withStatus(SessionStatus status) {
        this.status = status;
        return this;
    }

    public EnrollmentBuilder withType(SessionType type) {
        this.type = type;
        return this;
    }


    public EnrollmentBuilder withStudents(Students students) {
        this.students = students;
        return this;
    }


    public Enrollment build() {
        return new Enrollment(status, type, students);
    }
}
