package nextstep.courses.domain;

import java.util.List;

public class SessionJoins {
    private final List<SessionJoin> sessionJoins;

    public SessionJoins(List<SessionJoin> sessionJoins) {
        this.sessionJoins = sessionJoins;
    }

    public long isApproveStatusCount() {
        return sessionJoins.stream().filter(SessionJoin::isApproveStatus).count();
    }

    public void add(SessionJoin sessionJoin) {
        sessionJoins.add(sessionJoin);
    }

    public void addAll(List<SessionJoin> sessionJoin) {
        sessionJoins.addAll(sessionJoin);
    }

    public List<SessionJoin> value() {
        return sessionJoins;
    }
}
