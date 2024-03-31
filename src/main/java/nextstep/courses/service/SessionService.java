package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public void joinSession(NsUser loginUser, Session session) {
        session.join(loginUser);
    }
}
