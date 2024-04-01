package nextstep.courses.domain;

import static nextstep.courses.domain.SessionStatus.RECRUIT;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.CanNotJoinSessionException;
import nextstep.courses.InvalidSessionException;
import nextstep.users.domain.NsUser;

public class Session {

    protected Long id;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected SessionCoverImage coverImage;
    protected SessionStatus status = SessionStatus.PREPARE;
    protected List<NsUser> learners = new ArrayList<>();
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, SessionStatus.PREPARE, createdAt);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate,
        SessionCoverImage coverImage, SessionStatus status, LocalDateTime createdAt) {
        this.id = id;
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.status = status;
        this.createdAt = createdAt;
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidSessionException("강의 종료일이 시작일보다 앞섭니다");
        }
    }

    public void join(NsUser learner) {
        validateJoinable();
        learners.add(learner);
    }

    protected void validateJoinable() {
        if (status != RECRUIT) {
            throw new CanNotJoinSessionException("모집중 상태가 아닙니다");
        }
    }

    public List<NsUser> getLearners() {
        return learners;
    }

    public Long getId() {
        return id;
    }
}
