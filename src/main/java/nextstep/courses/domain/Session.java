package nextstep.courses.domain;

import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Session {
    private static AtomicLong autoGenId = new AtomicLong(1L);

    private Long id;
    private Long courseId;
    private int price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private CoverImage coverImage;
    private SessionType sessionType;
    private SessionStatus sessionStatus;
    private int availableSlots;

    public Session(Long courseId, CoverImage coverImage, SessionType sessionType, SessionStatus sessionStatus) {
        this.id = autoGenId.getAndIncrement();
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
    }

    public Session(Long courseId, CoverImage coverImage, SessionType sessionType, SessionStatus sessionStatus, int price, int availableSlots) {
        if (SessionType.FREE.isFree(sessionType)) {
            throw new IllegalArgumentException("해당 강의는 무료수업이므로 가격을 정할 수 없습니다.");
        }

        this.id = autoGenId.getAndIncrement();
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.price = price;
        this.availableSlots = availableSlots;
    }

    public Session(Long courseId) {
        this.id = autoGenId.getAndIncrement();
        this.courseId = courseId;
    }

    public void register(Course course) {
        if (SessionType.PAID.equals(this.sessionType)) {
            this.availableSlots--;
        }

        Long courseId = course.addSession(this);
        this.courseId = courseId;
    }

    public boolean isPaymentCorrect(Payment payment) {
        return payment.isAmountCorrect(this.price);
    }

    public boolean isFree() {
        return this.sessionType.equals(SessionType.FREE);
    }

    public boolean isOpen() {
        return this.sessionStatus.equals(SessionStatus.OPEN);
    }

    public void openSession() {
        this.sessionStatus = SessionStatus.OPEN;
    }

    public boolean isAvailable() {
        return this.availableSlots > 0;
    }
}
