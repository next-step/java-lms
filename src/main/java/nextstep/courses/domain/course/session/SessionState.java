package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;

public class SessionState {
    private SessionType sessionType;

    private Long amount;

    private int quota;

    public SessionState() {
        this.sessionType = SessionType.FREE;
        this.amount = 0L;
        this.quota = Integer.MAX_VALUE;
    }

    public SessionState(SessionType sessionType, Long amount, int quota) {
        this.sessionType = sessionType;
        this.amount = amount;
        this.quota = quota;
    }

    public boolean sameAmount(Long amount) {
        return Objects.equals(this.amount, amount);
    }

    public boolean charged() {
        return this.sessionType.charged();
    }

    public boolean chargedAndFull(List<NsUser> applicants) {
        return this.sessionType == SessionType.CHARGE && this.quota == applicants.size();
    }
}
