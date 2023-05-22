package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final String coverImage;
    private final PaymentType paymentType;
    private final SessionStatus sessionStatus;

    public Session(LocalDateTime startAt, LocalDateTime endAt, String coverImage, PaymentType paymentType, SessionStatus sessionStatus) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.sessionStatus = sessionStatus;
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

    public boolean isPreparing() {
        return sessionStatus == SessionStatus.PREPARING;
    }

    public boolean isRecruiting() {
        return sessionStatus == SessionStatus.RECRUITING;
    }

    public boolean isClosed() {
        return sessionStatus == SessionStatus.CLOSED;
    }
}
