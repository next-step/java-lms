package nextstep.courses.domain;

import nextstep.common.CommunicationTerm;
import nextstep.common.domain.Image;

import java.util.Date;
import java.util.List;

@CommunicationTerm("강의")
public class Session {
    private SessionId sessionId;
    private Date startDate;
    private Date endDate;
    private Image coverImage;
    private Long price;
    private SessionStatus status;
    private Long maxStudentCount;
    private List<Enrolment> enrolments;

    public Session(Long price, Long maxStudentCount) {
        this.price = price;
        this.maxStudentCount = maxStudentCount;
        this.startDate = new Date();
        this.endDate = new Date(System.currentTimeMillis() + 100000);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String coverImageUrl() {
        return this.coverImage.getImageUrl();
    }

    public void toFreeSession() {
        this.price = 0L;
    }

    public boolean isFreeSession() {
        return this.price == 0L;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void toPreparingState() {
        this.status = SessionStatus.PREPARING;
    }

    public void toRecruitingState() {
        this.status = SessionStatus.RECRUITING;
    }

    public void toCloseState() {
        this.status = SessionStatus.CLOSED;
    }

}
