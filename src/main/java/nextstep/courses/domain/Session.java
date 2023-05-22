package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final String coverImage;
    private final PaymentType paymentType;

    public Session(LocalDateTime startAt, LocalDateTime endAt, String coverImage, PaymentType paymentType) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public boolean isFree() {
        return paymentType == PaymentType.FREE;
    }
    public boolean isPaid() {
        return paymentType == PaymentType.PAID;
    }
}
