package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final Long id;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private SessionImage image;
    private final SessionStatus status;
    private Long price;
    private final int capacity;
    private List<Student> students;

    protected Session() {
        this(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate) {
        this(startDate, endDate, 0L, Integer.MAX_VALUE);
    }

    public Session(Long price, int capacity) {
        this(LocalDateTime.now(), LocalDateTime.now().plusMonths(1), price, capacity);
    }

    public Session(SessionStatus status) {
        this(null, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, status, 0L, Integer.MAX_VALUE);
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate, Long price, int capacity) {
        this(null, startDate, endDate, null, SessionStatus.OPEN, price, capacity);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, Long price, int capacity) {
        validateSessionDates(startDate, endDate);

        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.price = price;
        this.status = status;
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    private void validateSessionDates(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("start date must be before end date");
        }
    }

    public Payment enroll(Student student) {
        if (students.contains(student)) {
            throw new IllegalArgumentException("already enrolled");
        }
        if (status != SessionStatus.OPEN) {
            throw new IllegalArgumentException("session is not open");
        }
        if (price > 0 && students.size() >= capacity) {
            throw new IllegalArgumentException("student limit exceeded");
        }
        student.pay(price);
        students.add(student);
        return new Payment("0L", id, student.getNsUserId(), price);
    }

}
