package nextstep.sessions.domain.builder;

import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionStatus;

public class SessionRegisterDetailsBuilder {

    private long id = 1L;

    private Price price;

    private SessionStatus recruiting = SessionStatus.RECRUITING;

    private int maxOfStudents = 40;

    public SessionRegisterDetailsBuilder withPrice(Price price) {
        this.price = price;
        return this;
    }

    public SessionRegisterDetails build() {
        return new SessionRegisterDetails(id, price, recruiting, maxOfStudents);
    }

}
