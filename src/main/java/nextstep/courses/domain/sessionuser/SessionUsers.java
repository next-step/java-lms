package nextstep.courses.domain.sessionuser;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.session.Session;

public class SessionUsers {

    private List<SessionUser> sessionUsers = new ArrayList<>();

    private SessionUsers() {
    }

    public void addSessionUser(SessionUser sessionUser) {
        validateEnrollmentCount(sessionUser.session());
        sessionUsers.add(sessionUser);
    }

    private void validateEnrollmentCount(Session session) {
        if (session.isExceededMaxEnrollment(sessionUsers.size() + 1)) {
            throw new IllegalArgumentException("강의 최대 수강 인원을 초과했습니다.");
        }
    }
}
