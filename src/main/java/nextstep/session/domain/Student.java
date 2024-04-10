package nextstep.session.domain;

import nextstep.common.domain.BaseEntity;
import nextstep.session.dto.StudentDto;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Student {

    private final Long id;
    private final Long sessionId;
    private final NsUser user;
    private final BaseEntity baseEntity;

    public Student(NsUser nsUser) {
        this(0L, 0L, nsUser, new BaseEntity());
    }

    public Student(long sessionId, NsUser nsUser) {
        this(0L, sessionId, nsUser, new BaseEntity());
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

    public long getUserId() {
        return this.user.getId();
    }

    public NsUser getUser() {
        return user;
    }

    public StudentDto toDto() {
        return new StudentDto(
                this.id,
                this.sessionId,
                this.user.getUserId(),
                this.baseEntity.isDeleted(),
                this.baseEntity.getCreatedAt(),
                this.baseEntity.getLastModifiedAt()
        );
    }

    public void delete() {
        this.baseEntity.delete(LocalDateTime.now());
    }
}
