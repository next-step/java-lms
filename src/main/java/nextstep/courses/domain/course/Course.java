package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.session.Session;
import nextstep.courses.error.exception.SessionRegisterFailException;
import nextstep.payments.domain.Payment;

public class Course extends BaseEntity {
    private Long id;

    private String title;

    private Long creatorId;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public Session registerSession(Session session, Payment payment) {
        if (session.isRegistrationAvailable() && session.isPaymentAmountSameTuitionFee(payment)) {
            session.addRegistrationCount();
            return session;
        }
        throw new SessionRegisterFailException();
    }

}
