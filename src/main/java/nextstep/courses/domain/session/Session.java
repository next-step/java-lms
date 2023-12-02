package nextstep.courses.domain.session;

import nextstep.courses.type.SessionStatus;

public class Session {

    private Long id;
    private String title;
    private SessionPeriod sessionPeriod;
    private SessionStatus status;
    private Price price;

    public Session(String title, SessionPeriod sessionPeriod, Price price, SessionStatus status) {
        this(null, title, sessionPeriod, price, status);
    }

    public Session(Long id, String title, SessionPeriod sessionPeriod, Price price, SessionStatus status) {
        this.id = id;
        this.title = title;
        this.sessionPeriod = sessionPeriod;
        this.price = price;
        this.status = status;
    }

    public String title() {
        return this.title;
    }

    public boolean isFree(){
        return price.isFree();
    }
}
