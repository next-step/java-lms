package nextstep.courses.domain.session.enrollment;

import nextstep.users.domain.NsUser;

public class EnrollmentRequest {
    private long sessionId;
    private NsUser user;
    private long fee;
    private long amount;

    public EnrollmentRequest(long sessionId, NsUser user, long fee, long amount) {
        this.sessionId = sessionId;
        this.user = user;
        this.fee = fee;
        this.amount = amount;
    }

    public NsUser getUser() {
        return user;
    }

    public long getFee() {
        return fee;
    }

    public long getAmount() {
        return amount;
    }
}
