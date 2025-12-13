package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private ImageFile imageFile;
    private SessionPeriod period;
    private SessionStatus sessionStatus;
    private EnrollmentRule enrollmentRule;
    private Enrollments enrollments;

    public Session(ImageFile imageFile, SessionPeriod period, SessionStatus sessionStatus, EnrollmentRule enrollmentRule, Enrollments enrollments) {
        this.imageFile = imageFile;
        this.period = period;
        this.sessionStatus = sessionStatus;
        this.enrollmentRule = enrollmentRule;
        this.enrollments = enrollments;
    }

    public void enroll(Enrollment enrollment) {
        validationRecruiting();
        enrollments.enroll(enrollment);
    }

    private void validationRecruiting() {
        if (!sessionStatus.enableRecruiting()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청할 수 있습니다.");
        }
    }
}
