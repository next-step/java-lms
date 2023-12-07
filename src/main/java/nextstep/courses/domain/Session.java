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
    protected SessionState state;
    protected Participants participants;


    protected Session(CoverImage coverImage, LocalDate startDate, LocalDate endDate, SessionState state, LocalDateTime createdAt) {
        this(null, coverImage, startDate, endDate, state, createdAt, null);
    }

    protected Session(CoverImage coverImage, LocalDate startDate, LocalDate endDate, SessionState state, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, coverImage, startDate, endDate, state, createdAt, updatedAt);
    }

    protected Session(Long id, CoverImage coverImage, LocalDate startDate, LocalDate endDate, SessionState state, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.coverImage = coverImage;
        this.progressPeriod = new ProgressPeriod(startDate, endDate);
        this.state = state;
        this.participants = new Participants(new HashSet<>());
    }


    public long id() {
        return id;
    }

    public abstract void apply(Payment payment);

    protected void validateState() {
        if (state.isNotRecruiting()) {
            throw new SessionStateException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다");
        }
    }

    public ProgressPeriod getProgressPeriod() {
        return progressPeriod;
    }

    public String state() {
        return state.toString();
    }

    public CoverImage coverImage() {
        return coverImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Session session = (Session) o;
        return Objects.equals(coverImage, session.coverImage) && Objects.equals(progressPeriod, session.progressPeriod) && state == session.state && Objects.equals(participants, session.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coverImage, progressPeriod, state, participants);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id() +
                "coverImage=" + coverImage +
                ", progressPeriod=" + progressPeriod +
                ", state=" + state +
                ", participants=" + participants +
                ", createdAt= " + createdAt() +
                ", updateAt=" + updatedAt() +
                '}';
    }
}
