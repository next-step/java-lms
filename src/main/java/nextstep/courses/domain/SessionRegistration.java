package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionRegistration {
    private final List<SessionJoin> sessionJoins = new ArrayList<>();
    private final SessionStatus sessionStatus;
    private final SessionRecruitStatus sessionRecruitStatus;
    private final int maxUserCount;

    public SessionRegistration(SessionStatus sessionStatus, SessionRecruitStatus sessionRecruitStatus, int maxUserCount) {
        this.sessionStatus = sessionStatus;
        this.sessionRecruitStatus = sessionRecruitStatus;
        this.maxUserCount = maxUserCount;
    }

    public SessionJoin register(Session session, NsUser user) {
        if (this.sessionRecruitStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("강의가 모집중이지 않습니다.");
        }

        if (this.sessionStatus.isClose()) {
            throw new IllegalArgumentException("강의가 종료되었습니다.");
        }


        if (isAlreadyJoined(session, user)) {
            throw new IllegalArgumentException("이미 등록된 유저입니다.");
        }

        if (isSessionFull(maxUserCount)) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }

        SessionJoin applyedSessionJoin = SessionJoin.apply(session, user);
        sessionJoins.add(applyedSessionJoin);

        return applyedSessionJoin;
    }

    private boolean isAlreadyJoined(Session session, NsUser user) {
        return sessionJoins.stream()
                .anyMatch(join -> join.isAlreadyJoin(session, user));
    }

    private boolean isSessionFull(int maxUserCount) {
        return sessionJoins.size() >= maxUserCount;
    }

    public void addUser(Session session, NsUser nsUser) {
        LocalDateTime now = LocalDateTime.now();
        sessionJoins.add(SessionJoin.apply(session, nsUser));
    }

    public List<SessionJoin> getSessionJoins() {
        return sessionJoins;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionRecruitStatus getSessionRecruitStatus() {
        return sessionRecruitStatus;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    @Override
    public String toString() {
        return "SessionRegistration{" +
                ", sessionJoins=" + sessionJoins +
                ", sessionStatus=" + sessionStatus +
                ", maxUserCount=" + maxUserCount +
                '}';
    }
}
