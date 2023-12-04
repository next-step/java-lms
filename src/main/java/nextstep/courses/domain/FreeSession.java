package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FreeSession extends Session {

    public FreeSession(long id, CoverImage image, LocalDate start, LocalDate end, SessionState state, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, image, start, end, state, createdAt, updatedAt);
    }

    public FreeSession(long id, CoverImage image, LocalDate start, LocalDate end, SessionState state, LocalDateTime createdAt) {
        super(id, image, start, end, state, createdAt, null);
    }


    @Override
    public void apply(Payment payment) {
        validateState();
        this.participants = participants.add(payment.findUser());
    }

}
