package nextstep.courses.domain;

import nextstep.common.Communication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Communication("기수")
public class Term {
    private Long termId;
    private List<Session> sessions = new ArrayList<>();

    public Term() {
    }

    public void addSessions(Session... sessions) {
        this.sessions.addAll(Arrays.asList(sessions));
    }

    public boolean includeSession(Session session) {
        return this.sessions.contains(session);
    }
}
