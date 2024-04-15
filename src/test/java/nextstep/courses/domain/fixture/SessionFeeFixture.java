package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.SessionFee;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;

public class SessionFeeFixture {

    public static final Long SESSION_FEE = 800_000L;

    public static SessionFee sessionFee(Long fee) {
        return new SessionFee(SESSION_ID, fee);
    }

    public static SessionFee sessionFee() {
        return new SessionFee(SESSION_ID, SESSION_FEE);
    }

}
