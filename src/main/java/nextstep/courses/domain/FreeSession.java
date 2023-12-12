package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session {

    public FreeSession(Long id, List<CoverImage> coverImageList, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, coverImageList, start, end, progressState, recruitState, createdAt, updatedAt);
    }

    public FreeSession(List<CoverImage> coverImageList, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt) {
        super(null, coverImageList, start, end, progressState, recruitState, createdAt, null);
    }

    public FreeSession(List<CoverImage> coverImageList, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(null, coverImageList, start, end, progressState, recruitState, createdAt, updatedAt);
    }


    @Override
    public void apply(Payment payment) {
        validateState();
        this.participants = participants.add(payment.findUser());
    }

}
