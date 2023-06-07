package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionRegistration {
    private final Session session;
    private final List<SessionJoin> sessionJoins = new ArrayList<>();
    private final SessionStatus sessionStatus;
    private final int maxUserCount;

    public SessionRegistration(Session session, SessionStatus sessionStatus, int maxUserCount) {
        this.session = session;
        this.sessionStatus = sessionStatus;
        this.maxUserCount = maxUserCount;
    }


    public void register(NsUser user) {
        if (sessionStatus != SessionStatus.OPEN) {
            throw new IllegalArgumentException("수강신청은 모집중일때만 등록이 가능합니다.");
        }

        if (isAlreadyJoined(sessionJoins, user)) {
            throw new IllegalArgumentException("이미 등록된 유저입니다.");
        }

        if (isSessionFull(sessionJoins, maxUserCount)) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }

        SessionJoin sessionJoin = createSessionJoin(user);
        sessionJoins.add(sessionJoin);
    }

    private boolean isAlreadyJoined(List<SessionJoin> sessionJoins, NsUser user) {
        return sessionJoins.stream()
                .anyMatch(join -> join.isAlreadyJoin(session, user));
    }

    private boolean isSessionFull(List<SessionJoin> sessionJoins, int maxUserCount) {
        return sessionJoins.size() >= maxUserCount;
    }

    private SessionJoin createSessionJoin(NsUser user) {
        LocalDateTime now = LocalDateTime.now();
        return new SessionJoin(session, user, now, now);
    }

    public void addUser(NsUser nsUser) {
        LocalDateTime now = LocalDateTime.now();
        sessionJoins.add(new SessionJoin(session, nsUser, now, now));
    }


    public Session getSession() {
        return session;
    }

    public List<SessionJoin> getSessionJoins() {
        return sessionJoins;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    @Override
    public String toString() {
        return "SessionRegistration{" +
                "session=" + session +
                ", sessionJoins=" + sessionJoins +
                ", sessionStatus=" + sessionStatus +
                ", maxUserCount=" + maxUserCount +
                '}';
    }
}
