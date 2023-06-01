package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SessionRegistration {
    private final List<NsUser> users = new ArrayList<>();
    private final SessionStatus sessionStatus;
    private final int maxUserCount;

    private SessionRegistration(SessionStatus sessionStatus, int maxUserCount) {
        this.sessionStatus = sessionStatus;
        this.maxUserCount = maxUserCount;
    }

    public static SessionRegistration of(SessionStatus sessionStatus, int maxUserCount) {
        return new SessionRegistration(sessionStatus, maxUserCount);
    }

    public void addUser(NsUser user) {
        validateSessionStatus(sessionStatus);
        validateUserCount();
        validateUserDuplicated(user);
        this.users.add(user);
    }

    private void validateSessionStatus(SessionStatus sessionStatus) {
        if (sessionStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("해당 강의는 모집중이 아닙니다.");
        }
    }

    private void validateUserCount() {
        if (this.maxUserCount <= users.size()) {
            throw new IllegalArgumentException("최대 수강 인원을 초과했습니다.");
        }
    }

    private void validateUserDuplicated(NsUser user) {
        Set<NsUser> userSet = users.stream()
                .collect(Collectors.toSet());

        if (!userSet.add(user)) {
            throw new IllegalArgumentException("이미 등록 되었습니다.");
        }
    }

    public List<NsUser> getUsers() {
        return users;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }
}
