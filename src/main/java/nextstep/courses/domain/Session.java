package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private SessionPeriod period;
    private SessionImage image;
    private SessionStatus status;
    private Long price;
    private final Students students;

    private Session(Long id, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, Long price, int capacity) {
        this.id = id;
        this.period = new SessionPeriod(startDate, endDate);
        this.image = image;
        this.status = status;
        this.price = price;
        this.students = new Students(capacity);
    }

    public static Session createFreeSession(LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status) {
        return new Session(null, startDate, endDate, image, status, 0L, Integer.MAX_VALUE);
    }

    public static Session createPaidSession(LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, Long price, int capacity) {
        return new Session(null, startDate, endDate, image, status, price, capacity);
    }

    public Payment enroll(Student student) {
        if (status != SessionStatus.OPEN) {
            throw new IllegalArgumentException("session is not open");
        }

        students.register(student, price);
        return new Payment("0L", id, student.getNsUserId(), price);
    }

}
