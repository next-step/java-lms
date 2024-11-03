package nextstep.courses.domain;

import nextstep.courses.MaxStudentCapacityException;
import nextstep.courses.PaymentStudentNsUserNotMatchException;
import nextstep.courses.RecruitmentClosedException;
import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.payments.PaymentMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PaidSession extends Session {
    public static final String MAX_STUDENT_CAPACITY_MESSAGE = "강의 최대 수강인원을 초과하였습니다.";
    public static final String PAYMENT_MISMATCH_MESSAGE = "금액이 맞지 않습니다.";
    public static final String NOT_MATCHED_PAYMENT_STUDENT_NS_USER_MESSAGE = "결제 유저와 수강신청 유저가 다릅니다.";
    private final int maxRegisterCount;
    private final long amount;

    public PaidSession(long id,
                       long courseId,
                       DateRange dateRange,
                       CoverImage coverImage,
                       Status status,
                       List<CoverImage> coverImages,
                       Instructor instructor,
                       ProcessStatus processStatus,
                       RecruitmentStatus recruitmentStatus,
                       int maxRegisterCount,
                       long amount,
                       long creatorId,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        super(id, courseId, Category.PAID, dateRange, coverImage, status, coverImages, instructor, processStatus, recruitmentStatus, creatorId, createdAt, updatedAt);
        this.maxRegisterCount = maxRegisterCount;
        this.amount = amount;
    }

    public PaidSession(long id,
                       long courseId,
                       Category category,
                       DateRange dateRange,
                       CoverImage coverImage,
                       Status status,
                       long instructorId,
                       ProcessStatus processStatus,
                       RecruitmentStatus recruitmentStatus,
                       int maxRegisterCount,
                       long amount,
                       long creatorId,
                       LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        super(id, courseId, category, dateRange, coverImage, status, List.of(), instructorId, processStatus, recruitmentStatus, creatorId, createdAt, updatedAt);
        this.maxRegisterCount = maxRegisterCount;
        this.amount = amount;
    }

    public void register(Payment payment, Student student) {
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
        Long payingUserId = payingUser.getId();
        Long studentUserId = student.getNsUserId();
        if (!payingUserId.equals(studentUserId)) {
            throw new PaymentStudentNsUserNotMatchException(NOT_MATCHED_PAYMENT_STUDENT_NS_USER_MESSAGE);
        }
        this.students.add(student);
    }

    public int getMaxRegisterCount() {
        return maxRegisterCount;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PaidSession that = (PaidSession) o;
        return maxRegisterCount == that.maxRegisterCount && amount == that.amount && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxRegisterCount, amount, students);
    }

    @Override
    public String toString() {
        return "PaidSession{" +
                "maxRegisterCount=" + maxRegisterCount +
                ", amount=" + amount +
                ", id=" + id +
                ", courseId=" + courseId +
                ", category=" + category +
                ", dateRange=" + dateRange +
                ", coverImages=" + coverImages +
                ", instructorId=" + instructorId +
                ", processStatus=" + processStatus +
                ", recruitmentStatus=" + recruitmentStatus +
                ", students=" + students +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
