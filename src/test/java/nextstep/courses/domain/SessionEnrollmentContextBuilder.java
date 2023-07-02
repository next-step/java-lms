package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class SessionEnrollmentContextBuilder {
    private List<Student> students = new ArrayList<>();
    private SessionEnrollmentContext.SessionStatus progressSessionStatus = SessionEnrollmentContext.SessionStatus.NOT_STARTED;
    private long maxEnrollment;

    public static SessionEnrollmentContextBuilder builder() {
        return new SessionEnrollmentContextBuilder();
    }

    public SessionEnrollmentContextBuilder withStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public SessionEnrollmentContextBuilder withProgressStatus(SessionEnrollmentContext.SessionStatus progressSessionStatus) {
        this.progressSessionStatus = progressSessionStatus;
        return this;
    }

    public SessionEnrollmentContextBuilder withMaxEnrollment(long maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
        return this;
    }

    public SessionEnrollmentContext build() {
        return new SessionEnrollmentContext(maxEnrollment, progressSessionStatus, students);
    }
}
