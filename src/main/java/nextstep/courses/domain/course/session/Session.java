package nextstep.courses.domain.course.session;

import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.course.session.apply.Applies;
import nextstep.courses.domain.course.session.apply.ApproveCancel;
import nextstep.courses.domain.course.session.image.Images;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session extends BaseEntity {
    private final Long id;

    private final Images images;

    private final Applies applies;

    private final SessionDetail sessionDetail;

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
            throw new IllegalArgumentException("이미지를 추가해야 합니다.");
        }

        if (applies == null) {
            throw new IllegalArgumentException("지원자를 추가해야 합니다.");
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
        return new Enrollment(this.id, this.applies, this.sessionDetail);
    }

    public ApproveCancel approve() {
        return new ApproveCancel(this.applies);
    }

    public ApproveCancel cancel() {
        return new ApproveCancel(this.applies);
    }

    public Session changeOnReady(LocalDate date) {
        SessionDetail changedSessionDetail = this.sessionDetail.changeOnReady(date);
        return new Session(id, images, applies, changedSessionDetail, creatorId(), createdAt(), updatedAt());
    }

    public Session changeOnGoing(LocalDate date) {
        SessionDetail changedSessionDetail = this.sessionDetail.changeOnGoing(date);
        return new Session(id, images, applies, changedSessionDetail, creatorId(), createdAt(), updatedAt());
    }

    public Session changeOnEnd(LocalDate date) {
        SessionDetail changedSessionDetail = this.sessionDetail.changeOnEnd(date);
        return new Session(id, images, applies, changedSessionDetail, creatorId(), createdAt(), updatedAt());
    }

    public Long id() {
        return this.id;
    }

    public Images images() {
        return images;
    }

    public SessionDetail sessionDetail() {
        return sessionDetail;
    }

    public SessionDuration sessionDuration() {
        return sessionDetail.sessionDuration();
    }

    public SessionState sessionState() {
        return sessionDetail.sessionState();
    }

    public SessionProgressStatus sessionProgressStatus() {
        return sessionDetail.sessionProgressStatus();
    }

    public SessionRecruitStatus sessionRecruitStatus() {
        return sessionDetail.sessionRecruitStatus();
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
