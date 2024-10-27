package nextstep.courses.domain;

import nextstep.courses.MaxStudentCapacityException;
import nextstep.payments.PaymentMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaidSession {

    public static final String MAX_STUDENT_CAPACITY_MESSAGE = "강의 최대 수강인원을 초과하였습니다.";
    public static final String PAYMENT_MISMATCH_MESSAGE = "금액이 맞지 않습니다.";
    private final long id;
    private final int maxRegisterCount;
    private final long amount;
    private final List<NsUser> students;

    private PaidSession(long id,
                        int maxRegisterCount,
                        long amount,
                        List<NsUser> students) {
        this.id = id;
        this.maxRegisterCount = maxRegisterCount;
        this.amount = amount;
        this.students = students;
    }

    public PaidSession(long id,
                       int maxRegisterCount,
                       long amount,
                       NsUser... students) {
        this(id, maxRegisterCount, amount, new ArrayList<>(List.of(students)));
    }

    public void register(Payment payment) {
        if (maxRegisterCount <= students.size()) {
            throw new MaxStudentCapacityException(MAX_STUDENT_CAPACITY_MESSAGE);
        }
        if (!payment.matchAmount(amount)) {
            throw new PaymentMismatchException(PAYMENT_MISMATCH_MESSAGE);
        }
        NsUser payingUser = payment.payingUser();
        this.students.add(payingUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidSession that = (PaidSession) o;
        return id == that.id && maxRegisterCount == that.maxRegisterCount && amount == that.amount && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, students, maxRegisterCount, amount);
    }
}
