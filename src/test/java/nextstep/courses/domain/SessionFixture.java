package nextstep.courses.domain;

import nextstep.courses.domain.vo.Price;
import nextstep.courses.domain.vo.SessionState;

public class SessionFixture {

    public static Session create(AttendeesSlut slut, SessionState sessionState, Price price) {
        return new Session(1L, 1L, slut, null, sessionState, new Attendees(), price, null);
    }

}
