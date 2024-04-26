package nextstep.courses.domain;

import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Enrollment {

    private Students students;
    private int studentCapacity;
    private Money fee;

    public Enrollment(Students students, int studentCapacity, long fee) {
        this.students = students;
        this.studentCapacity = studentCapacity;
        this.fee = new Money(fee);
    }

    public Enrollment(int studentCapacity, long fee) {
        this(new Students(), studentCapacity, fee);
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

    private boolean isFull() {
        return studentCapacity == students.size();
    }
}
