package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentSession that = (EnrollmentSession) o;
        return Objects.equals(id, that.id) && Objects.equals(information, that.information) && Objects.equals(enrollment, that.enrollment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, information, enrollment);
    }
}
