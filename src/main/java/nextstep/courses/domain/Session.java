package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;
    private SessionPeriod period;
    private SessionImage image;
    private SessionStatus status;
    private Long price;
    private int capacity;
    private List<Student> students;


    public Session(Long price, int capacity) {
        this(null, LocalDateTime.now(), LocalDateTime.now().plusMonths(1),null, SessionStatus.OPEN, price, capacity);
    }

    public Session(SessionStatus status) {
        this(null, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, status, 0L, Integer.MAX_VALUE);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, Long price, int capacity) {
        this.id = id;
        this.period = new SessionPeriod(startDate, endDate);
        this.image = image;
        this.price = price;
        this.status = status;
        this.capacity = capacity;
        this.students = new ArrayList<>();
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
