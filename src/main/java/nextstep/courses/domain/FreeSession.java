package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class FreeSession extends Session {

    public FreeSession(Duration duration, Images images) {
        super(duration, images);
    }

    public FreeSession(Duration duration, Images images, LocalDateTime createdAt) {
        super(duration, images, createdAt);
    }

    public FreeSession(Duration duration, Images images, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(duration, images, createdAt, updatedAt);
    }

    public FreeSession(Duration duration, Images images, SessionStatus status) {
        super(duration, images, status);
    }

    public FreeSession(Duration duration, Images images, SessionStatus status, LocalDateTime createdAt) {
        super(duration, images, status, createdAt, null);
    }

    public FreeSession(Duration duration, Images images, SessionStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(duration, images, status, createdAt, updatedAt);
    }

    public FreeSession(Long id, Duration duration, Images images, SessionStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, duration, images, status, createdAt, updatedAt);
    }

    public void apply(Payment payment, NsUser nsUser, LocalDateTime applyAt) {
        validateStatus();
        addStudent(nsUser, applyAt);
    }
}
