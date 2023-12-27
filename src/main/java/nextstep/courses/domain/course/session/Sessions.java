package nextstep.courses.domain.course.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sessions implements Iterable<Session> {
    private final List<Session> sessions;

    public Sessions() {
        this(new ArrayList<>());
    }

    public Sessions(List<Session> sessions) {
        validate(sessions);

        this.sessions = sessions;
    }

    private void validate(List<Session> sessions) {
        checkSessionsSizeIsValid(sessions);
    }

    private void checkSessionsSizeIsValid(List<Session> sessions) {
        if(sessions == null) {
            throw new IllegalArgumentException("강의는 빈 값이 아니어야 합니다.");
        }
    }

    public int size() {
        return this.sessions.size();
    }

    public void add(Session session) {
        checkSessionAlreadyExisted(session);
        this.sessions.add(session);
    }

    private void checkSessionAlreadyExisted(Session session) {
        if (this.sessions.contains(session)) {
            throw new IllegalArgumentException("이미 강의를 추가 하였습니다.");
        }
    }

    @Override
    public Iterator<Session> iterator() {
        return this.sessions.iterator();
    }
}
