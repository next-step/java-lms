package nextstep.courses.domain.sessionuser;

import java.util.HashSet;
import java.util.Set;
import nextstep.courses.domain.session.Session;

public class SessionUsers {

    private Set<SessionUser> values = new HashSet<>();

    public SessionUsers() {
    }

    public void addSessionUser(SessionUser sessionUser) {
        validateEnrollmentCount(sessionUser.session());
        values.add(sessionUser);
    }

    private void validateEnrollmentCount(Session session) {
        if (session.isExceededMaxEnrollment(values.size() + 1)) {
            throw new IllegalArgumentException("강의 최대 수강 인원을 초과했습니다.");
        }
    }
}
