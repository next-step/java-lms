package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
    private final Long id;
    private final String title;
    private final SessionStatus status;
    private final SessionPeriod period;
    private final SessionThumbnail thumbnail;
    private final SessionPrice price;
    private final Enrollment enrollment;

    public Session(Long id, String title, SessionStatus status, LocalDateTime startDate, LocalDateTime endDate,
                  SessionThumbnail thumbnail, SessionType type, int maxEnrollment, int price) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.period = new SessionPeriod(startDate, endDate);
        this.thumbnail = thumbnail;
        this.price = new SessionPrice(type, price);
        this.enrollment = new Enrollment(maxEnrollment);
    }

    public void enroll(Payment payment) {
        if (!status.isRecruiting()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청이 가능합니다.");
        }

        if (price.isPaid()) {
            validatePayment(payment);
        }

        if (price.isPaid()) {
            enrollment.enroll();
        }
    }

    private void validatePayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("유료 강의는 결제가 필요합니다.");
        }

        price.validatePayment(payment.getAmount());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public LocalDateTime getStartDate() {
        return period.getStartDate();
    }

    public LocalDateTime getEndDate() {
        return period.getEndDate();
    }

    public SessionThumbnail getThumbnail() {
        return thumbnail;
    }

    public SessionType getType() {
        return price.isPaid() ? SessionType.PAID : SessionType.FREE;
    }

    public int getMaxEnrollment() {
        return enrollment.getMaxEnrollment();
    }

    public int getPrice() {
        return price.getPrice();
    }

    public int getCurrentEnrollment() {
        return enrollment.getCurrentEnrollment();
    }
} 