package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;

import java.time.LocalDateTime;

public class SessionEntity extends BaseEntity {

    private Long id;
    private final Long courseId;
    private final String type;
    private final String status;
    private final Integer capacity;
    private final Long fee;

    public SessionEntity(Long id, Long courseId, String type, String status, Integer capacity, Long fee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.courseId = courseId;
        this.type = type;
        this.status = status;
        this.capacity = capacity;
        this.fee = fee;
    }

    public SessionEntity(Session session, LocalDateTime createdAt) {
        super(createdAt);
        this.courseId = session.getCourseId();
        this.type = session.getType().get();

        SessionEnrollment enrollment = session.getEnrollment();
        this.status = enrollment.getStatus().get();
        this.capacity = enrollment.getCapacity().get();
        this.fee = enrollment.getFee().get();
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Long getFee() {
        return fee;
    }
}
