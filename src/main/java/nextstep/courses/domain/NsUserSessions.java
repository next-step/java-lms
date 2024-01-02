package nextstep.courses.domain;

import java.util.Arrays;
import java.util.List;

public class NsUserSessions {
    private List<NsUserSession> nsUserSessions;

    public NsUserSessions(NsUserSession... nsUserSessions) {
        this(Arrays.asList(nsUserSessions));
    }

    public NsUserSessions(List<NsUserSession> nsUserSessions) {
        this.nsUserSessions = nsUserSessions;
    }

    public Long approvedUserNumber() {
        return nsUserSessions.stream().filter(NsUserSession::isApproved).count();
    }

    @Override
    public String toString() {
        return "NsUserSessions{" +
                "nsUserSessions=" + nsUserSessions +
                '}';
    }
}
