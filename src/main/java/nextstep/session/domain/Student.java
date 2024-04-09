package nextstep.session.domain;

import nextstep.common.domain.BaseEntity;
import nextstep.users.domain.NsUser;

public class Student {

    private final Long id;
    private final Long sessionId;
    private final NsUser user;
    private final BaseEntity baseEntity;

    public Student(NsUser nsUser) {
        this(0L, 0L, nsUser, new BaseEntity());
    }

    public Student(Long id, Long sessionId, NsUser user, BaseEntity baseEntity) {
        this.id = id;
        this.sessionId = sessionId;
        this.user = user;
        this.baseEntity = baseEntity;
    }

    public boolean matchUser(Student student) {
        return getUserId() == student.getUserId();
    }

    private long getUserId() {
        return this.user.getId();
    }
}
