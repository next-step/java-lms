package nextstep.courses.domain;

import java.time.LocalDateTime;

import nextstep.payments.domain.Payment;


public class Session {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private SessionImage image;

    private SessionType type;

    private SessionStatus status;

    private int capacity;

    private int enrolledCount;

    private int 수강료;
    
    public Session() {}

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionType type, SessionStatus status, int capacity) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.type = type;
        this.status = status;
        this.capacity = capacity;
    }

    public void apply(Payment payment) throws Exception {
        // 강의상태
        if (isAvaliable()) {

            // 수강인원
            if (isPaid() && capacity == enrolledCount || isPaid() && capacity < enrolledCount) {
            
                // 수강료
                if (payment.getAmount() != 수강료) {
                    throw new Exception("수강료와 결제금액이 달라 수강신청이 불가능합니다.");
                }

            }

            this.enrolledCount++;
            
        }
        
    }

    public boolean isPaid() {
        return this.type == SessionType.PAID;
    }

    public boolean isAvaliable() {
        return this.status == SessionStatus.RECRUITING;
    }

}
