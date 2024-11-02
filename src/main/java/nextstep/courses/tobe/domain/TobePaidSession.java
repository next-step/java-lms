package nextstep.courses.tobe.domain;

import nextstep.courses.MaxStudentCapacityException;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.PaymentStudentNsUserNotMatchException;
import nextstep.courses.tobe.RecruitmentClosedException;
import nextstep.courses.tobe.domain.session.TobeCoverImage;
import nextstep.payments.PaymentMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TobePaidSession extends TobeSession {
    public static final String MAX_STUDENT_CAPACITY_MESSAGE = "강의 최대 수강인원을 초과하였습니다.";
    public static final String PAYMENT_MISMATCH_MESSAGE = "금액이 맞지 않습니다.";
    public static final String NOT_MATCHED_PAYMENT_STUDENT_NS_USER_MESSAGE = "결제 유저와 수강신청 유저가 다릅니다.";
    private final int maxRegisterCount;
    private final long amount;
    private final List<TobeStudent> students;

    public TobePaidSession(long id,
                           long courseId,
                           DateRange dateRange,
                           TobeCoverImage coverImage,
                           ProcessStatus processStatus,
                           RecruitmentStatus recruitmentStatus,
                           int maxRegisterCount,
                           long amount,
                           long creatorId,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        super(id,
                courseId,
                Category.PAID,
                dateRange,
                coverImage,
                processStatus,
                recruitmentStatus,
                creatorId,
                createdAt,
                updatedAt
        );
        this.maxRegisterCount = maxRegisterCount;
        this.amount = amount;
        this.students = new ArrayList<>();
    }


    public void register(Payment payment, TobeStudent student) {
        if (maxRegisterCount <= students.size()) {
            throw new MaxStudentCapacityException(MAX_STUDENT_CAPACITY_MESSAGE);
        }
        if (!payment.matchAmount(amount)) {
            throw new PaymentMismatchException(PAYMENT_MISMATCH_MESSAGE);
        }
        if (RecruitmentStatus.CLOSED.equals(recruitmentStatus)) {
            throw new RecruitmentClosedException(NOT_ALLOWED_REGISTER_TO_CLOSED_SESSION_MESSAGE);
        }
        NsUser payingUser = payment.payingUser();
        // TODO: exception 핸들링
        if (!payingUser.getId().equals(student.getNsUserId())) {
            throw new PaymentStudentNsUserNotMatchException(NOT_MATCHED_PAYMENT_STUDENT_NS_USER_MESSAGE);
        }
        this.students.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TobePaidSession that = (TobePaidSession) o;
        return maxRegisterCount == that.maxRegisterCount && amount == that.amount && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxRegisterCount, amount, students);
    }
}
