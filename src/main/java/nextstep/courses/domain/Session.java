package nextstep.courses.domain;

import nextstep.common.CommunicationTerm;
import nextstep.common.domain.Image;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@CommunicationTerm("강의")
public class Session {
    private SessionId sessionId;
    private Date startDate;
    private Date endDate;
    private Image coverImage;
    private Long price;
    @NotNull
    private SessionStatus status;
    private Long maxStudentCount;
    private List<Enroll> enrolls = new ArrayList<>();

    public Session(Long price, Long maxStudentCount) {
        this.price = price;
        this.maxStudentCount = maxStudentCount;
        this.startDate = new Date();
        this.endDate = new Date(System.currentTimeMillis() + 100000);
        this.status = SessionStatus.CLOSED;
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

    public void registerCoverImage(Image image) {
        this.coverImage = image;
    }

    public void enroll(Enroll... enroll) {
        this.enrolls.addAll(Arrays.asList(enroll));
    }

    public boolean enrollCheck(Enroll enroll) {
        return enrolls.contains(enroll);
    }
}
