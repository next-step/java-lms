package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionApplies {

    private static final String CLOSED_RECRUITING = "수강 인원이 마감되었습니다.";

    List<SessionApply> values = new ArrayList<>();

    public SessionApplies() {
    }

    public SessionApplies(List<SessionApply> values) {
        this.values = values;
    }

    public SessionApply apply(Long id, Session session, NsUser user) {
        if (session.isClosed(currentCount())) {
            throw new IllegalStateException(CLOSED_RECRUITING);
        }
        SessionApply sessionApply = new SessionApply(id, session, user);
        values.add(sessionApply);
        return sessionApply;
    }

    private int currentCount() {
        return values.size();
    }


}
