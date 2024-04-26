package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Sessions {

    private List<Session> sessions;

    private Sessions(Session... sessions) {
        this.sessions = new ArrayList<>(Arrays.asList(sessions));
    }

    public static Sessions from(Session session) {
        return new Sessions(session);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void register(Long sessionId, NsUser user, Payment payment) throws CannotRegisterException {
        Session findSession = sessions.stream()
                .filter(session -> session.hasId(sessionId))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("session id가 존재하지 않습니다." + sessionId));
        findSession.register(user, payment);
    }

    public void register(Long sessionId, NsUser user) throws CannotRegisterException {
        Session findSession = sessions.stream()
                .filter(session -> session.hasId(sessionId))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("session id가 존재하지 않습니다." + sessionId));
        findSession.register(user);
    }

    public int size() {
        return sessions.size();
    }
}
