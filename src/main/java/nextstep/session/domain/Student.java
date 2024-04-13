package nextstep.session.domain;

import nextstep.common.domain.BaseEntity;
import nextstep.session.dto.StudentVO;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Student {

    private final Long id;
    private final Long sessionId;
    private final String userId;
    private final BaseEntity baseEntity;

    public Student(NsUser nsUser) {
        this(nsUser.getUserId());
    }

    public Student(String userId) {
        this(0L, 0L, userId, new BaseEntity());
    }

    public Student(long sessionId, NsUser nsUser) {
        this(sessionId, nsUser.getUserId());
    }

    public Student(long sessionId, String userId) {
        this(0L, sessionId, userId, new BaseEntity());
    }

    public Student(Long id, Long sessionId, NsUser nsUser, BaseEntity baseEntity) {
        this(id, sessionId, nsUser.getUserId(), baseEntity);
    }

    public Student(Long id, Long sessionId, String userId, boolean deleted, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this(id, sessionId, userId, new BaseEntity(deleted, createdAt, lastModifiedAt));
    }

    public Student(Long id, Long sessionId, String userId, BaseEntity baseEntity) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.baseEntity = baseEntity;
    }

    public boolean matchUser(Student student) {
        return Objects.equals(getUserId(), student.getUserId());
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return this.userId;
    }

    public StudentVO toVO() {
        return new StudentVO(
                this.id,
                this.sessionId,
                this.userId,
                this.baseEntity.isDeleted(),
                this.baseEntity.getCreatedAt(),
                this.baseEntity.getLastModifiedAt()
        );
    }

    public void delete() {
        this.baseEntity.delete(LocalDateTime.now());
    }
}
