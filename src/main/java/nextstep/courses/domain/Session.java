package nextstep.courses.domain;

import nextstep.courses.CannotEnrollSessionException;
import nextstep.courses.domain.vo.CoverImage;
import nextstep.courses.domain.vo.Price;
import nextstep.courses.domain.vo.SessionPeriod;
import nextstep.courses.domain.vo.SessionState;
import nextstep.users.domain.NsUser;

class Session {

    private Long id;
    private Long courseId;
    private final AttendeesSlut attendeesSlut;
    private final SessionPeriod sessionPeriod;
    private final SessionState sessionState;
    private final Attendees attendees;
    private final Price price;
    private final CoverImage coverImage;

    public Session(Long id, Long courseId, AttendeesSlut attendeesSlut, SessionPeriod sessionPeriod, SessionState sessionState, Attendees attendees, Price price, CoverImage coverImage) {
        this.id = id;
        this.courseId = courseId;
        this.attendeesSlut = attendeesSlut;
        this.sessionPeriod = sessionPeriod;
        this.sessionState = sessionState;
        this.attendees = attendees;
        this.price = price;
        this.coverImage = coverImage;
    }

    public void enroll(EnrollCommand enrollCommand) {
        try {
            enrollUser(enrollCommand.userToEnroll(price));
        } catch (IllegalStateException exception) {
            throw  new CannotEnrollSessionException(exception.getMessage());
        }
    }

    private void enrollUser(NsUser nsUser) {
        if (!attendeesSlut.available()) {
            throw new CannotEnrollSessionException("Session is fully enrolled");
        }
        attendees.add(nsUser);
        attendeesSlut.plus();
    }

}
