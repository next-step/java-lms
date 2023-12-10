package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FreeSession extends Session {

    /**
     * AS_IS : 모집상태 미반영 생성자
     *
     * @param id
     * @param image
     * @param start
     * @param end
     * @param progressState
     * @param createdAt
     * @param updatedAt
     */
    public FreeSession(Long id, CoverImage image, LocalDate start, LocalDate end, SessionProgressState progressState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, image, start, end, progressState, createdAt, updatedAt);
    }

    public FreeSession(CoverImage image, LocalDate start, LocalDate end, SessionProgressState state, LocalDateTime createdAt) {
        super(image, start, end, state, createdAt);
    }

    public FreeSession(CoverImage image, LocalDate start, LocalDate end, SessionProgressState state, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(image, start, end, state, createdAt, updatedAt);
    }


    /**
     * TO_BE : 모집상태 반영 생성자
     *
     * @param id
     * @param image
     * @param start
     * @param end
     * @param progressState
     * @param recruitState
     * @param createdAt
     * @param updatedAt
     */
    public FreeSession(Long id, CoverImage image, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, image, start, end, progressState, recruitState, createdAt, updatedAt);
    }

    public FreeSession(CoverImage image, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt) {
        super(image, start, end, progressState, recruitState, createdAt);
    }

    public FreeSession(CoverImage image, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(image, start, end, progressState, recruitState, createdAt, updatedAt);
    }


    @Override
    public void apply(Payment payment) {
        validateState();
        this.participants = participants.add(payment.findUser());
    }

}
