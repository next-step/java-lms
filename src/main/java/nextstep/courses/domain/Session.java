package nextstep.courses.domain;

import java.util.Date;

public class Session {

    private Date startDate;
    private Date endDate;
    private ThumbnailInfo thumbnailInfo;
    private PaymentTypeEnum paymentType;
    private int enrollments;
    private SessionStatusEnum status;
    private int maximumEnrollments;

    public Session() {
        this.maximumEnrollments = 30;
    }

    public Session(int maximumEnrollments) {
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
}
