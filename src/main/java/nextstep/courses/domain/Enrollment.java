package nextstep.courses.domain;

import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Enrollment {

    private Students students;
    private int studentCapacity;
    private Money fee;

    private Enrollment(Students students) {
        this(students, 0, 0);
    }

    private Enrollment(Students students, int studentCapacity, long fee) {
        this.students = students;
        this.studentCapacity = studentCapacity;
        this.fee = new Money(fee);
    }

    public static Enrollment createFreeEnrollment(Students students) {
        return new Enrollment(students);
    }

    public static Enrollment createPaidEnrollment(Students students, int studentCapacity, long fee) {
        return new Enrollment(students, studentCapacity, fee);
    }

    public void enroll(NsUser student) {
        this.students.admit(student);
    }

    public void enroll(NsUser student, Payment payment) throws CannotRegisterException {
        if (isFull()) {
            throw new CannotRegisterException("수강인원이 가득 찾습니다");
        }
        if (!payment.isEqualAmount(fee)) {
            throw new CannotRegisterException("결제 금액이 다릅니다");
        }
        this.students.admit(student);
    }

    public int countOfEnrolledStudent() {
        return this.students.size();
    }

    private boolean isFull() {
        return studentCapacity == students.size();
    }
}
