package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class FreeSession extends Session {

    public FreeSession(Duration duration, Image image) {
        super(duration, image);
    }

    public FreeSession(Duration duration, Image image, SessionStatus status) {
        super(duration, image, status);
    }

    public FreeSession(Long id, Duration duration, Image image, SessionStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, duration, image, status, createdAt, updatedAt);
    }

    public void apply(Payment payment, NsUser nsUser) {
        validateStatus();
        addStudent(nsUser);
    }
}
