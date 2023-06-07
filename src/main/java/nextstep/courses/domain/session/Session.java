package nextstep.courses.domain.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import nextstep.courses.domain.charge.Charge;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.exceptions.AlreadyEnrollmentException;
import nextstep.users.domain.NsUser;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Session {

    private Sessioninfo sessioninfo;

    private Charge charge;

    private final SessionPeriod sessionPeriod;

    private final Enrollment enrollment;

    public void enroll(NsUser nsUser, List<NsUser> students) throws AlreadyEnrollmentException {
        enrollment.enroll(nsUser, students);
    }

}
