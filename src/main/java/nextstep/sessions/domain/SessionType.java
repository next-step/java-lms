package nextstep.sessions.domain;

public enum SessionType {
    PAY("pay"),
    FREE("free"),
    ;
    private final String sessionType;

    SessionType(String sessionType) {
        this.sessionType = sessionType;
    }
}
