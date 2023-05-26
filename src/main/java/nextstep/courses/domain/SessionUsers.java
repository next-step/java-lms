package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class SessionUsers {
    private static final String ALERT_TEXT = "최대 수강 인원을 초과할 수 없습니다.";

    private final int maxEnrollment;
    private final List<SessionUser> sessionUsers;

    public SessionUsers(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
        this.sessionUsers = new ArrayList<>();
    }

    public int getUserCount() {
        return sessionUsers.size();
    }

    public void addEnroll(SessionUser sessionUser) {
        validateEnrollment();
        sessionUsers.add(sessionUser);
    }

    private void validateEnrollment() {
        if (sessionUsers.size() >= maxEnrollment) {
            throw new IllegalArgumentException(ALERT_TEXT);
        }
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public List<SessionUser> getSessionUsers() {
        return sessionUsers;
    }
}
