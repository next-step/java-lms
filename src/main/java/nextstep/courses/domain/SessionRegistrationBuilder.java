package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionRegistrationBuilder {
    private List<NsUser> users;
    private SessionStatus sessionStatus;
    private int maxUserCount;

    private SessionRegistrationBuilder(){
        this.users = new ArrayList<>();
    }

    public static SessionRegistrationBuilder aSessionRegistrationBuilder() {
        return new SessionRegistrationBuilder();
    }

    public SessionRegistrationBuilder withUser(NsUser nsUser) {
        this.users.add(nsUser);
        return this;
    }

    public SessionRegistrationBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }

    public SessionRegistrationBuilder withMaxUserCount(int maxUserCount) {
        this.maxUserCount = maxUserCount;
        return this;
    }

    public SessionRegistration build() {
        SessionRegistration sessionRegistration = SessionRegistration.of(sessionStatus, maxUserCount);
        users.forEach(sessionRegistration::addUser);
        return sessionRegistration;
    }
}
