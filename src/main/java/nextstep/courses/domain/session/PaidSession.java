package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.common.SystemTimeStamp;
import nextstep.courses.domain.Student;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaidSession extends Session {
    private int maxStudentCount;
    private Long sessionFee;

    public static PaidSession feeOf(long id, String title, long courseId,
                                    EnrollmentStatus enrollmentStatus, LocalDate startDate,
                                    LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt,
                                    int maxStudentCount, Long sessionFee) {
        return new PaidSession(new SessionInfo(id, title, courseId, SessionType.PAID),
                new SessionPlan(enrollmentStatus, startDate, endDate),
                new SystemTimeStamp(createdAt, updatedAt),
                maxStudentCount, sessionFee);
    }

    public PaidSession(SessionInfo sessionInfo, SessionPlan sessionPlan, SystemTimeStamp systemTimeStamp,
                       int maxStudentCount, Long sessionFee) {
        super(sessionInfo, sessionPlan, systemTimeStamp);
        this.maxStudentCount = maxStudentCount;
        this.sessionFee = sessionFee;
    }

    @Override
    public void signUp(Student student) {
        validateAvailableStudentCount();
        validatePayInfo(student, getPayInfo(student));
        super.signUp(student);
    }

    private Payment getPayInfo(Student student) {
        return Payment.paidOf("tmp", super.getId(), student.getNsUserId(), this.sessionFee);  // 결제가 완료됐다고 가정하기 위함.
    }

    private void validatePayInfo(Student student, Payment payment) {
        if (payment.getSessionId() != this.getId()) {
            throw new CannotSignUpException("해당 강의 결제이력이 없습니다.");
        }
        if (student.getSessionId() != payment.getNsUserId()) {
            throw new CannotSignUpException("결제자와 신청자의 정보가 일치하지 않습니다.");
        }
        if (payment.isNotSameSessionFee(sessionFee)) {
            throw new CannotSignUpException("결제금액과 수강료가 일치하지 않습니다.");
        }
    }

    private void validateAvailableStudentCount() throws CannotSignUpException {
        if (maxStudentCount == super.getStudentCount()) {
            throw new CannotSignUpException("최대 수강 인원을 초과했습니다.");
        }
    }

    @Override
    public String toString() {
        return "PaidSession{" +
                "maxStudentCount=" + maxStudentCount +
                ", sessionFee=" + sessionFee +
                '}';
    }
}
