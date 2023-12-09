package nextstep.sessions.domain.data.session;

import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.registration.Registration;
import nextstep.sessions.domain.data.registration.Registrations;
import nextstep.users.domain.NsUser;

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

    public Registration registration(Session session, NsUser loginUser, Payment payment) {
        return new Registration(session, loginUser, payment);
    }
}
