package nextstep.session.domain;

public class SessionName {

    private final String sessionName;

    public SessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public SessionName editSessionName(String sessionName) {
        return new SessionName(sessionName);
    }
}
