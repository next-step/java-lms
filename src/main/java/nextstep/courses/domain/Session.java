package nextstep.courses.domain;

import nextstep.courses.exception.SessionException;

public class Session {

    private SessionImage sessionImage;

    private SessionPeriod sessionPeriod;

    private SessionState sessionState;

    private SessionType sessionType;

    public Session(SessionImage sessionImage, SessionPeriod sessionPeriod, SessionState sessionState, SessionType sessionType) {
        this.sessionImage = sessionImage;
        this.sessionPeriod = sessionPeriod;
        this.sessionState = sessionState;
        this.sessionType = sessionType;
    }

    public void register(){
        if(!isOpen()){
            throw new SessionException("현재 강의가 모집중이지 않아 수강 신청을 할 수가 없습니다.");
        }
    }

    private boolean isOpen() {
        return sessionState == SessionState.OPEN;
    }

}
