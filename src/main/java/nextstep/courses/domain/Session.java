package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private Long id;
    private int enrollments;
    private int maximumEnrollments;
    private String thumbnailUrl;
    private PaymentTypeEnum paymentType;
    private SessionStatusEnum status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Session(Long id, int enrollments, int maximumEnrollments, String thumbnailUrl, PaymentTypeEnum paymentType, SessionStatusEnum status, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.enrollments = enrollments;
        this.maximumEnrollments = maximumEnrollments;
        this.thumbnailUrl = thumbnailUrl;
        this.paymentType = paymentType;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Session(PaymentTypeEnum paymentType, LocalDateTime startDate, LocalDateTime endDate){
        this.enrollments = 0;
        this.status = SessionStatusEnum.READY;
        this.paymentType = paymentType;
        this.startDate = startDate;
        this.endDate = endDate;

    }
    public Session(PaymentTypeEnum paymentType) {
        this(paymentType, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        this.maximumEnrollments = 30;
    }

    public Session(PaymentTypeEnum paymentType, int maximumEnrollments) {
        this(paymentType, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        this.maximumEnrollments = maximumEnrollments;
    }

    public void ready(){
        statusChange(SessionStatusEnum.READY);
    }

    public void recruit(){
        statusChange(SessionStatusEnum.RECRUIT);
    }

    public void closed(){
        statusChange(SessionStatusEnum.CLOSED);
    }

    public void registerSession() throws Exception {
        if(canPossible()){
            plusEnrollment();
        }
    }

    private void statusChange(SessionStatusEnum status) {
        this.status = status;
    }

    public boolean canPossible() {
        return status == SessionStatusEnum.RECRUIT;
    }

    public void plusEnrollment() throws Exception {
        if(enrollments+1>maximumEnrollments){
            throw new Exception("해당 강의는 수강인원을 초과 하였습니다.");
        }

        enrollments+=1;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getPaymentType() {
        return paymentType.name();
    }

    public int getEnrollments() {
        return enrollments;
    }

    public String getStatus() {
        return status.name();
    }

    public int getMaximumEnrollments() {
        return maximumEnrollments;
    }
}
