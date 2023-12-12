package nextstep.courses.controller;

import nextstep.courses.application.SessionService;
import nextstep.users.domain.NsUser;

public class SessionController {
    private SessionService sessionService;

    public void enroll(NsUser loginUser) {
        sessionService.enroll(loginUser, 1L);
    }
}
