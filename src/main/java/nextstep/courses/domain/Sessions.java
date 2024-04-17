package nextstep.courses.domain;

import nextstep.courses.exception.SessionAlreadyExistException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sessions {

    private List<Session> sessionList = new ArrayList<>();

    public Sessions() {
    }

    public Sessions(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    public List<Session> sessions() {
        return Collections.unmodifiableList(sessionList);
    }

    public void add(Session session) throws SessionAlreadyExistException {
        if(sessionList.stream().anyMatch(existingSession -> existingSession.equals(session))) {
            throw new SessionAlreadyExistException("중복된 강의가 있습니다.");
        }
        sessionList.add(session);
    }
}
