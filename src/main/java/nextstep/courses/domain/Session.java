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


    public Session(Long price, int capacity) {
        this(null, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, SessionStatus.OPEN, price, capacity);
    }

    public Session(SessionStatus status) {
        this(null, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, status, 0L, Integer.MAX_VALUE);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, Long price, int capacity) {
        validateCapacity(price, capacity);

        this.id = id;
        this.period = new SessionPeriod(startDate, endDate);
        this.image = image;
        this.price = price;
        this.status = status;
        this.students = new Students(capacity);
    }

    private void validateCapacity(Long price, int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
        if (price == 0 && capacity != Integer.MAX_VALUE) {
            throw new IllegalArgumentException("capacity must be Integer.MAX_VALUE when price is 0");
        }
    }

    public Payment enroll(Student student) {
        if (status != SessionStatus.OPEN) {
            throw new IllegalArgumentException("session is not open");
        }

        students.register(student, price);
        return new Payment("0L", id, student.getNsUserId(), price);
    }

}
