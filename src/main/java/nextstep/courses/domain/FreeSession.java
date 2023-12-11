package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session {
    /**
     * AS_IS : 강의 커버 이미지 단건
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
        super(null, image, start, end, progressState, recruitState, createdAt, null);
    }

    public FreeSession(CoverImage image, LocalDate start, LocalDate end, SessionProgressState progressState, Boolean recruitState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(null, image, start, end, progressState, recruitState, createdAt, updatedAt);
    }

    /**
     * TO_BE : 강의 커버 이미지 다건
     *
     * @param id
     * @param coverImageList
     * @param start
     * @param end
     * @param progressState
     * @param recruitState
     * @param createdAt
     * @param updatedAt
     */

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
