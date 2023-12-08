package nextstep.sessions.domain.data;

import java.util.List;

import nextstep.sessions.domain.data.vo.*;

public class Enrollment {

    private final SessionInfo sessionInfo;
    private final Registrations registrations;

    public Enrollment(SessionInfo sessionInfo, List<Registration> registrations) {
        this.sessionInfo = sessionInfo;
        this.registrations = new Registrations(registrations);
    }

    public void enroll(Registration registration) {
        sessionInfo.validateEnrollment(registrations.size(), registration.payment());
        registrations.validateDuplicateEnrollment(registration.user());
    }
}
