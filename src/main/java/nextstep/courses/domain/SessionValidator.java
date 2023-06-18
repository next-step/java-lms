package nextstep.courses.domain;

import java.util.HashSet;
import java.util.Set;

import nextstep.users.domain.NsUser;

public class SessionValidator {

    private final Long maxCount;
    private final Set<NsUser> nsUsers;
    private SessionState sessionState;

    public SessionValidator(Long maxCount, SessionState sessionState) {
        this.maxCount = maxCount;
        this.sessionState = sessionState;
        nsUsers = new HashSet<>();
    }

    public SessionValidator(Long maxCount, String sessionStateString) {
        this.maxCount = maxCount;
        this.sessionState = sessionState;
        nsUsers = new HashSet<>();
    }

    public void addPerson(NsUser nsUser) {
        if (!sessionState.isRecruitable()) {
            throw new IllegalArgumentException("해당 강의는 수강신청중이 아닙니다.");
        }

        if (nsUsers.size() >= maxCount) {
            throw new IllegalArgumentException("수강신청 정원을 넘었습니다.");
        }

        if (nsUsers.contains(nsUser)) {
            throw new IllegalArgumentException("중복 신청입니다.");
        }
        nsUsers.add(nsUser);
    }

    public SessionState sessionState() {
        return sessionState;
    }

    public Long maxCount() {
        return maxCount;
    }
}
