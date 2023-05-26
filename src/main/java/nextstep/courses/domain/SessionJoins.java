package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
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

    public List<SessionJoin> approve(List<NsUser> nsUsers) {
        List<SessionJoin> result = new ArrayList<>();
        sessionJoins.stream()
                    .filter(sessionJoin -> nsUsers.contains(sessionJoin.getNsUser()))
                    .forEach(sessionJoin -> {
                        sessionJoin.approve();
                        result.add(sessionJoin);
                    });
        return result;
    }
}
