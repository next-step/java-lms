package nextstep.courses.domain;

import static nextstep.courses.domain.SessionStatus.RECRUIT;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import nextstep.courses.CanNotJoinSessionException;
import nextstep.courses.InvalidSessionException;
import nextstep.courses.infrastructure.dto.LearnerDto;
import nextstep.users.domain.NsUser;

public class Session {

    protected Long id;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected SessionCoverImage coverImage;
    protected SessionStatus status = SessionStatus.PREPARE;
    protected Set<NsUser> learners = new HashSet<>();
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

    public LearnerDto join(NsUser learner) {
        validateJoinable(learner);
        learners.add(learner);
        return new LearnerDto(learner.getId(), id);
    }

    protected void validateJoinable(NsUser learner) {
        if (status != RECRUIT) {
            throw new CanNotJoinSessionException("모집중 상태가 아닙니다");
        }
        if (learners.contains(learner)) {
            throw new CanNotJoinSessionException("이미 수강중인 강의입니다");
        }
    }

    public Set<NsUser> getLearners() {
        return learners;
    }

    public Long getId() {
        return id;
    }
}
