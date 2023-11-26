package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.courses.exception.SessionFullException;

import java.util.Objects;

public class PaidEnrollmentStrategy implements Enrollment {

    private final int capacity;

    private final Amount amount;

    private final Students students;

    public PaidEnrollmentStrategy(int capacity,
                                  Amount amount) {
        this(capacity, amount, new Students());
    }

    public PaidEnrollmentStrategy(int capacity,
                                  Amount amount,
                                  Students students) {
        this.capacity = capacity;
        this.amount = amount;
        this.students = students;
    }

    @Override
    public void enrol(Student student,
                      long payment) {
        amount.validateAmount(payment);

        if (students.isFull(capacity)) {
            throw new SessionFullException("수강 신청 인원이 마감 되었습니다.");
        }
        students.enrol(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidEnrollmentStrategy paidEnrollmentStrategy1 = (PaidEnrollmentStrategy) o;
        return capacity == paidEnrollmentStrategy1.capacity && Objects.equals(students, paidEnrollmentStrategy1.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, students);
    }
}
