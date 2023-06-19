package nextstep.session.domain.teacher;

import nextstep.BaseTime;
import nextstep.session.domain.Session;
import nextstep.users.domain.NsUser;

public class SessionTeacher {

    private final Long id;
    private final Long sessionId;
    private final Long nsUserId;
    private BaseTime baseTime;

    public SessionTeacher(Long id, Long sessionId, Long nsUserId) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.baseTime = new BaseTime();
    }

    public SessionTeacher(Long id, Session session, NsUser nsUser) {
        this.id = id;
        this.sessionId = session.getId();
        this.nsUserId = nsUser.getId();
        this.baseTime = new BaseTime();
    }

    public Long getId() {
        return id;
    }
}
