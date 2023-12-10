package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FreeSession extends Session {

    public FreeSession(Long id, CoverImage image, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, image, start, end, progressState, recruitState, createdAt, updatedAt);
    }

    public FreeSession(CoverImage image, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt) {
        this(null, image, start, end, progressState, recruitState, createdAt, null);
    }

    public FreeSession(CoverImage image, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, image, start, end, progressState, recruitState, createdAt, updatedAt);
    }


    @Override
    public void apply(Payment payment) {
        validateState();
        this.participants = participants.add(payment.findUser());
    }

}
