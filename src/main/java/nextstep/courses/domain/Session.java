package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.exception.SessionStateException;
import nextstep.payments.domain.Payment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

public abstract class Session extends BaseEntity {
    protected Long id;
    protected CoverImage coverImage;
    protected ProgressPeriod progressPeriod;
    protected SessionProgressState progressState;
    protected Boolean recruitState;
    protected Participants participants;
    

    protected Session(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate, SessionProgressState state, Boolean recruitState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.coverImage = coverImage;
        this.progressPeriod = new ProgressPeriod(startDate, endDate);
        this.recruitState = recruitState;
        this.progressState = state;
        this.participants = new Participants(new HashSet<>());
    }


    public Long id() {
        return id;
    }

    public abstract void apply(Payment payment);

    protected void validateState() {
        if (!recruitState) {
            throw new SessionStateException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다");
        }
    }

    public ProgressPeriod getProgressPeriod() {
        return progressPeriod;
    }

    public String progressState() {
        return progressState.toString();
    }

    public CoverImage coverImage() {
        return coverImage;
    }

    public Boolean recruitState() {
        return recruitState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Session session = (Session) o;
        return Objects.equals(coverImage, session.coverImage) && Objects.equals(progressPeriod, session.progressPeriod) && progressState == session.progressState && Objects.equals(participants, session.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coverImage, progressPeriod, progressState, participants);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id() +
                "coverImage=" + coverImage +
                ", progressPeriod=" + progressPeriod +
                ", state=" + progressState +
                ", participants=" + participants +
                ", createdAt= " + createdAt() +
                ", updateAt=" + updatedAt() +
                '}';
    }
}
