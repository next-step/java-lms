package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class SessionRegisterDetails {

    private final long id;

    private final Price price;

    private final SessionStatus sessionStatus;

    private int maxOfStudents;

    private Students students;

    public SessionRegisterDetails(long id, Price price, SessionStatus sessionStatus, int maxOfStudents) {
        this(id, price, sessionStatus, maxOfStudents, new Students(maxOfStudents));
    }

    public SessionRegisterDetails(long id, Price price, SessionStatus sessionStatus, int maxOfStudents, Students students) {
        this.id = id;
        this.price = price;
        this.sessionStatus = sessionStatus;
        this.maxOfStudents = maxOfStudents;
        this.students = students;
    }

    public Student enroll(Student student, List<Student> enrolledStudents, Payment payment) {
        if (this.sessionStatus.isNotInProgress()) {
            throw new IllegalArgumentException(String.format("현재 강의는 (%s)인 상태입니다.", this.sessionStatus));
        }
        if (this.price.isNotSamePrice(payment)) {
            throw new IllegalArgumentException("결제한 금액이 강의의 가격과 일치하지 않습니다.");
        }
        Students students = new Students(this.maxOfStudents, SessionType.PAID, new ArrayList<>(enrolledStudents)); // 그냥 enrolledStudents를 넣어주면 UnsupportedOperationException 발생
        students.enroll(student);
        return student;
    }

    public boolean isNotSamePrice(long amount) {
        return price.isNotSamePrice(amount);
    }

    public long getId() {
        return id;
    }
}
