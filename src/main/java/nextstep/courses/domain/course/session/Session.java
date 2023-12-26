package nextstep.courses.domain.course.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.session.apply.Applies;
import nextstep.courses.domain.course.session.image.Images;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session extends BaseEntity {
    private Long id;

    private Images images;

    private Applies applies;

    private SessionDetail sessionDetail;

    public Session(Images images, SessionDuration sessionDuration, SessionState sessionState,
                   Long creatorId, LocalDateTime date) {
        this(0L, images, new Applies(), sessionDuration, sessionState, SessionRecruitStatus.NOT_RECRUIT,
                SessionProgressStatus.READY, creatorId, date, null);
    }

    public Session(Long id, Images images, Applies applies, SessionDuration sessionDuration, SessionState sessionState,
                   SessionRecruitStatus sessionRecruitStatus, SessionProgressStatus sessionProgressStatus,
                   Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, images, applies, new SessionDetail(sessionDuration, sessionState, sessionProgressStatus, sessionRecruitStatus),
                creatorId, createdAt, updatedAt);
    }

    public Session(Long id, Images images, Applies applies, SessionDetail sessionDetail,
                   Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(creatorId, createdAt, updatedAt);
        if (images == null) {
            throw new IllegalArgumentException("이미지를 추가해야 합니다");
        }

        if (sessionDetail == null) {
            throw new IllegalArgumentException("강의 정보를 추가해야 합니다.");
        }

        this.id = id;
        this.images = images;
        this.applies = applies;
        this.sessionDetail = sessionDetail;
    }

    public Enrollment enrollment() {
        return new Enrollment(this.id, this.applies, this.getSessionState(),
                this.getSessionProgressStatus(), this.getSessionRecruitStatus());
    }

    public Approve approve() {
        return new Approve(this.applies, this.sessionDetail.getSessionState());
    }

    public Cancel cancel() {
        return new Cancel(this.applies, this.sessionDetail.getSessionState());
    }

    public void changeOnReady(LocalDate date) {
        this.sessionDetail.changeOnReady(date);
    }

    public void changeOnGoing(LocalDate date) {
        this.sessionDetail.changeOnGoing(date);
    }

    public void changeOnEnd(LocalDate date) {
        this.sessionDetail.changeOnEnd(date);
    }

    public void changeSessionState(SessionState updateSessionState) {
        this.sessionDetail.changeSessionState(updateSessionState);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int applyCount() {
        return this.applies.size();
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public SessionDetail getSessionDetail() {
        return sessionDetail;
    }

    public SessionDuration getDuration() {
        return sessionDetail.getDuration();
    }

    public SessionState getSessionState() {
        return sessionDetail.getSessionState();
    }

    public SessionProgressStatus getSessionProgressStatus() {
        return sessionDetail.getSessionStatus();
    }

    public SessionRecruitStatus getSessionRecruitStatus() {
        return sessionDetail.getRecruitStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", images=" + images +
                ", sessionDetail=" + sessionDetail +
                '}';
    }
}
