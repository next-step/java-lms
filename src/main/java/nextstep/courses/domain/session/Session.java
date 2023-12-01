package nextstep.courses.domain.session;

import nextstep.courses.type.SessionStatus;

public class Session {

    private Long id;
    private String title;
    private SessionPeriod sessionPeriod;
    private SessionStatus sessionStatus;
    private boolean isFree;
    private Integer price;
    private Integer maxParticipants;

    public Session(String title, SessionPeriod sessionPeriod, boolean isFree, int price, int maxParticipants, SessionStatus sessionStatus) {
        this(null, title, sessionPeriod, isFree, price, maxParticipants, sessionStatus);
    }

    public Session(Long id, String title, SessionPeriod sessionPeriod, boolean isFree, int price, int maxParticipants, SessionStatus sessionStatus) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.isFree = isFree;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.sessionStatus = sessionStatus;
    }

    public String title() {
        return this.title;
    }

    public boolean isFree(){
        return this.isFree;
    }
}
