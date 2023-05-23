package nextstep.courses.domain;

import java.util.Date;
import java.util.function.IntConsumer;

public class Session {
    private final int maximumEnrollments = 30;

    private Date startDate;
    private Date endDate;
    private ThumbnailInfo thumbnailInfo;
    private boolean isPaid;
    private int enrollments;
    private SessionStatusEnum status;

    public Session() {
    }

    public void statusChange(SessionStatusEnum status) {
        this.status = status;
    }

    public boolean isPossible() {
        return status == SessionStatusEnum.RECRUIT;
    }

    public void plusEnrollment() throws Exception {
        if(enrollments+1>maximumEnrollments){
            throw new Exception("해당 강의는 수강인원을 초과 하였습니다.");
        }

        enrollments+=1;
    }
}
