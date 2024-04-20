package nextstep.sessions.domain.builder;

import nextstep.sessions.domain.CountOfStudent;
import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionRegisterDetailsBuilder {

    private long id = 1L;

    private CountOfStudent countOfStudent = new CountOfStudentBuilder().build();

    private Price price;

    private SessionStatus recruiting = SessionStatus.RECRUITING;

    private List<NsUser> students = new ArrayList<>();

    private Session session;

    public SessionRegisterDetailsBuilder withSession(Session session) {
        this.session = session;
        return this;
    }

    public SessionRegisterDetailsBuilder withPrice(Price price) {
        this.price = price;
        return this;
    }

    public SessionRegisterDetails build() {
        return new SessionRegisterDetails(id, countOfStudent, price, recruiting, students, session);
    }

}
