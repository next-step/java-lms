package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private int sequence;
    private LocalDateTime start;
    private LocalDateTime end;
    private SessionImage image;
    private int price;
    private SessionStatus status;
    private int maxStudents;
    private List<Student> students;

    protected Session() {
    }

    public Session(int sequence, LocalDateTime start, LocalDateTime end, String image, int price, int maxStudents) {
        this.sequence = sequence;
        this.start = start;
        this.end = end;
        this.image = new SessionImage(image);
        this.price = price;
        this.status = SessionStatus.READY;
        this.maxStudents = maxStudents;
        this.students = new ArrayList<>();
    }

    public void enroll(Student student) {
        if (price > 0 && students.size() >= maxStudents) {
            throw new IllegalArgumentException("student limit exceeded");
        }
        student.pay(price);
        students.add(student);
    }
}
