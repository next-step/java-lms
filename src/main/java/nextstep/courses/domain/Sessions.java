package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {
    private List<Session> sessions = new ArrayList<>();

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Session findBy(Long sessionIdx) {
        return sessions.stream()
                .filter(item -> item.idx == sessionIdx)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("강의를 찾을 수 없습니다."));
    }

    public void add(PaidSession paidSession) {
        this.sessions.add(paidSession);
    }
}
