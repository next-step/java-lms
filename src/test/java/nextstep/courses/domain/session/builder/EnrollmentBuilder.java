package nextstep.courses.domain.session.builder;

import nextstep.courses.domain.session.Enrollment;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class EnrollmentBuilder {

    private NsUser user = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    private Long sessionId = 1L;

    public EnrollmentBuilder withUser(NsUser user) {
        this.user = user;
        return this;
    }

    public EnrollmentBuilder withSessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }


    public Enrollment build() {
        return new Enrollment(user, sessionId);
    }

}
