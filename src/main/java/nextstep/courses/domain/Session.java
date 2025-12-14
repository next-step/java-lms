package nextstep.courses.domain;

public class Session {
    private final Long id;
    private final ImageFile imageFile;
    private final SessionPeriod period;
    private final SessionStatus sessionStatus;
    private final EnrollmentRule enrollmentRule;
    private final Enrollments enrollments;

    public Session(Long id, ImageFile imageFile, SessionPeriod period, SessionStatus sessionStatus, EnrollmentRule enrollmentRule) {
        this(id, imageFile, period, sessionStatus, enrollmentRule, new Enrollments());
    }

    public Session(Long id, ImageFile imageFile, SessionPeriod period, SessionStatus sessionStatus, EnrollmentRule enrollmentRule, Enrollments enrollments) {
        this.id = id;
        this.imageFile = imageFile;
        this.period = period;
        this.sessionStatus = sessionStatus;
        this.enrollmentRule = enrollmentRule;
        this.enrollments = enrollments;
    }

    public int countEnrollments() {
        return enrollments.countEnrollments();
    }

    public void enroll(Enrollment enrollment) {
        validationRecruiting();

        enrollment.validateBelongsTo(this);

        enrollmentRule.validateMoney(enrollment.getMoney());
        enrollmentRule.validateCapacity(enrollments.countEnrollments());

        enrollments.enroll(enrollment);
    }

    public Long getId() {
        return id;
    }

    private void validationRecruiting() {
        if (!sessionStatus.enableRecruiting()) {
            throw new IllegalArgumentException("모집중인 강의만 수강 신청할 수 있습니다.");
        }
    }
}
