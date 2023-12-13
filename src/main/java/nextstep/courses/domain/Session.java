package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDate;

public class Session {
    private Long id;
    private Long courseId;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private long sessionPrice;
    private SessionImage sessionImage;
    private SessionStatus sessionStatus;
    private SessionFeeType sessionFeeType;
    private SessionStudentCount sessionStudentCount;

    public Session(Long id, Long courseId, LocalDate startDate, LocalDate endDate, long sessionPrice, SessionImage sessionImage, SessionStatus sessionStatus, SessionFeeType sessionFeeType, SessionStudentCount sessionStudentCount) {
        this.id = id;
        this.courseId = courseId;
        StartDate = startDate;
        EndDate = endDate;
        this.sessionPrice = sessionPrice;
        this.sessionImage = sessionImage;
        this.sessionStatus = sessionStatus;
        this.sessionFeeType = sessionFeeType;
        this.sessionStudentCount = sessionStudentCount;
    }

    public void register(Payment payment){
        validateStatus();
        validateStudentCount();
        isEqualPayment(payment);
        sessionStudentCount.addStudent();
    }

    private void validateStudentCount() {
        if(isPaid()){
            sessionStudentCount.validateStudentCount();
        }
    }

    private boolean isPaid() {
        return sessionFeeType == SessionFeeType.PAID;
    }

    private void validateStatus() {
        if(!ableRegister()){
            throw new IllegalArgumentException("수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
    }

    private boolean ableRegister() {
        return sessionStatus == SessionStatus.RECRUITING;
    }

    public void isEqualPayment(Payment payment) {
        if(!payment.isEqualPayment(sessionPrice)){
            throw new IllegalArgumentException("결제한 금액과 수강료가 일치할 때 수강 신청이 가능합니다.");
        }
    }
}
