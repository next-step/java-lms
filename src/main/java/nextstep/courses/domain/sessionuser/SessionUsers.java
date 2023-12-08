package nextstep.courses.domain.sessionuser;

import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionUsers {

    private List<SessionUser> sessionUsers = new ArrayList<>();

    public SessionUser addUser(NsUser nsUser, Session session) {
        if (!session.canRegisterNewUser(sessionUsers.size())) {
            throw new ExceedMaxAttendanceCountException("이미 최대 수강 인원이 다 찼습니다.");
        }
        SessionUser sessionUser = new SessionUser(nsUser, session);
        sessionUsers.add(sessionUser);
        return sessionUser;
    }

    public int totalAttendUsersCount() {
        return sessionUsers.size();
    }
}
