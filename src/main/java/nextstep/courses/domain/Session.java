package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

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
        this.enrollment = type == SessionType.FREE ? Enrollment.free() : Enrollment.paid(maxEnrollment);
    }

    public void enroll(NsUser user, Payment payment) {
        if (!status.isRecruiting()) {
            throw new IllegalStateException("모집중인 강의만 수강 신청이 가능합니다.");
        }

        if (price.isPaid()) {
            validatePaymentExists(payment);
            price.validatePayment(payment);
        }
        enrollment.enroll(user);
    }

    public List<NsUser> getEnrolledUsers() {
        return enrollment.getEnrolledUsers();
    }

    public boolean isPaid() {
        return price.isPaid();
    }

    public Long getId() {
        return id;
    }

    public SessionType getType() {
        return price.getType();
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

    private void validatePaymentExists(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("유료 강의는 결제가 필요합니다.");
        }
    }
}