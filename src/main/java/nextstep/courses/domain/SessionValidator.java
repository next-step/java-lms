package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

import nextstep.users.domain.NsUser;

public class SessionValidator {

    private final Set<NsUser> nsUsers;
    private SessionState sessionState;
    private SessionRecruitState sessionRecruitState;

    public SessionValidator(SessionState sessionState) {
        this(sessionState, SessionRecruitState.UN_RECRUITING);
    }

    public SessionValidator(String sessionStateString) {
        this.sessionState = SessionState.convert(sessionStateString);
        nsUsers = new HashSet<>();
    }

    public SessionValidator(SessionState sessionState, SessionRecruitState sessionRecruitState) {
        this.sessionState = sessionState;
        this.sessionRecruitState = sessionRecruitState;
        nsUsers = new HashSet<>();
    }

    public SessionValidator(Long maxCount, String sessionStateString) {
        this.maxCount = maxCount;
        this.sessionState = SessionState.convert(sessionStateString);
        nsUsers = new HashSet<>();
    }

    public void addPerson(NsUser nsUser) {
        if (!sessionRecruitState.isRecruitable()) {
            throw new IllegalArgumentException("해당 강의는 수강신청중이 아닙니다.");
        }

        if (nsUsers.contains(nsUser)) {
            throw new IllegalArgumentException("중복 신청입니다.");
        }
        nsUsers.add(nsUser);
    }

    public boolean isRecuritable() {
        return sessionRecruitState.isRecruitable();
    }

    public boolean isProceeding() {
        return SessionState.isProceeding(sessionState);
    }

    public boolean isPreparing() {
        return SessionState.isPreparing(sessionState);
    }
}
