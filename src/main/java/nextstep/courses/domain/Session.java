package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long sessionId;
    private int sequence;
    private LocalDateTime start;
    private LocalDateTime end;
    private SessionImage image;
    private Long price;
    private SessionStatus status;
    private int maxStudents;
    private List<Student> students;

    protected Session() {
    }

    public Session(int sequence, LocalDateTime start, LocalDateTime end, String image, Long price, int maxStudents) {
        this(sequence, start, end, image, price, maxStudents, SessionStatus.READY);
    }

    public Session(int sequence, LocalDateTime start, LocalDateTime end, String image, Long price, int maxStudents, SessionStatus status) {
        this(null, sequence, start, end, new SessionImage(image), price, status, maxStudents, new ArrayList<>());
    }

    public Session(Long sessionId, int sequence, LocalDateTime start, LocalDateTime end, SessionImage image, Long price, SessionStatus status, int maxStudents, List<Student> students) {
        this.sessionId = sessionId;
        this.sequence = sequence;
        this.start = start;
        this.end = end;
        this.image = image;
        this.price = price;
        this.status = status;
        this.maxStudents = maxStudents;
        this.students = students;
    }

    public Payment enroll(Student student) {
        if (status != SessionStatus.OPEN) {
            throw new IllegalArgumentException("session is not open");
        }
        if (price > 0 && students.size() >= maxStudents) {
            throw new IllegalArgumentException("student limit exceeded");
        }
        if (students.contains(student)) {
            throw new IllegalArgumentException("already enrolled");
        }
        student.pay(price);
        students.add(student);
        return new Payment("0L", sessionId, student.getNsUserId(), price);
    }

}
