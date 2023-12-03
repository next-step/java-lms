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

    public Session(Long courseId, CoverImage coverImage, SessionType sessionType, SessionStatus sessionStatus) {
        this.id = autoGenId.getAndIncrement();
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
    }

    public Session(Long courseId) {
        this.id = autoGenId.getAndIncrement();
        this.courseId = courseId;
    }

    public void register(Course course) {
        Long courseId = course.addSession(this);
        this.courseId = courseId;
    }

    public boolean isPaymentCorrect(Payment payment) {
        return payment.isAmountCorrect(this.price);
    }

    public boolean isFree() {
        return this.sessionType.equals(SessionType.FREE);
    }
}
