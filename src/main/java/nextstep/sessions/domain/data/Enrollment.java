package nextstep.sessions.domain.data;

import java.util.List;

import nextstep.sessions.domain.data.vo.*;

public class Enrollment {

    private final EnrollmentInfo enrollmentInfo;
    private final Registrations registrations;

    public Enrollment(EnrollmentInfo enrollmentInfo, List<Registration> registrations) {
        this.enrollmentInfo = enrollmentInfo;
        this.registrations = new Registrations(registrations);
    }

    public void enroll(Registration registration) {
        enrollmentInfo.validateEnrollment(registrations.size(), registration.payment());
        registrations.validateDuplicateEnrollment(registration.user());
    }
}
