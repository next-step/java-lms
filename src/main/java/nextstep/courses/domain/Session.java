package nextstep.courses.domain;

import nextstep.courses.domain.vo.CoverImage;
import nextstep.courses.domain.vo.Period;
import nextstep.users.domain.NsUser;

abstract class Session implements SessionStateful {

    private Long id;

    protected final Period period;
    protected final CoverImage coverImage;
    protected final Attendees attendees;
    protected final SessionState sessionState;

    protected Session(Period period, CoverImage coverImage, Attendees attendees, SessionState sessionState) {
        this.period = period;
        this.coverImage = coverImage;
        this.attendees = attendees;
        this.sessionState = sessionState;
    }


    abstract void enroll(NsUser nsUser, ChargeStrategy chargeStrategy);


    @Override
    public boolean preparing() {
        return sessionState == SessionState.PREPARATION;
    }

    @Override
    public boolean recruiting() {
        return sessionState == SessionState.RECRUITING;
    }

    @Override
    public boolean finished() {
        return sessionState == SessionState.FINISHED;
    }
}
