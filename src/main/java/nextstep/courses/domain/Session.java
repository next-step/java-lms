package nextstep.courses.domain;

import nextstep.courses.exception.ExceededStudentCount;
import nextstep.courses.exception.OutOfRegistrationPeriod;
import nextstep.image.domain.Image;
import nextstep.utils.CommunicationTerm;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@CommunicationTerm("강의")
public class Session {
    private SessionId sessionId;
    @NotNull
    private List<Enroll> enrolls = new ArrayList<>();
    private Image coverImage;
    @CommunicationTerm("기수")
    private Long term;
    @NotNull
    private Long price;
    @NotNull
    private SessionStatus status;
    @NotNull
    private Long maxStudentCount;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;

    public Session(SessionId sessionId, List<Enroll> enrolls, Image coverImage, Long term, Long price, SessionStatus status, Long maxStudentCount, Date startDate, Date endDate) {
        this.sessionId = sessionId;
        this.enrolls = enrolls;
        this.coverImage = coverImage;
        this.term = term;
        this.price = price;
        this.status = status;
        this.maxStudentCount = maxStudentCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Session of(Long price, Long maxStudentCount) {
        return new Session(
                null,
                new ArrayList<>(),
                null,
                1L,
                price,
                SessionStatus.RECRUITING,
                maxStudentCount,
                new Date(),
                new Date(System.currentTimeMillis() + 1000000)
        );
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

    public void enroll(Enroll... enrolls) {
        validateState();
        validateStudentCount(enrolls.length);
        this.enrolls.addAll(Arrays.asList(enrolls));
    }

    private void validateStudentCount(int count) {
        if (maxStudentCount < this.enrolls.size() + count) {
            throw new ExceededStudentCount();
        }
    }

    private void validateState() {
        if (this.status != SessionStatus.RECRUITING) {
            throw new OutOfRegistrationPeriod();
        }
    }

    public boolean enrollCheck(Enroll enroll) {
        return enrolls.contains(enroll);
    }

    public void adjustStudentCount(Long maxStudentCount) {
        this.maxStudentCount = maxStudentCount;
    }

    public long getTerm() {
        return this.term;
    }

    public void setTerm(long term) {
        this.term = term;
    }
}
