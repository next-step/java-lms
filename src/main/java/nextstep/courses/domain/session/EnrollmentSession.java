package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;

public class EnrollmentSession {

    private final Long id;

    private final SessionInformation information;

    private final Enrollment enrollment;

    public EnrollmentSession(Long id,
                             SessionInformation information,
                             Enrollment enrollment) {
        this.id = id;
        this.information = information;
        this.enrollment = enrollment;
    }

    public Attendee enroll(Long amount, Long userId) {
        information.validateApply();
        return enrollment.enroll(amount, userId, this.id);
    }
}
