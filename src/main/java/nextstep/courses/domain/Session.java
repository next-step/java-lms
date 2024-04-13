package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    protected List<SessionCoverImage> coverImages = new ArrayList<>();
    protected SessionStatus status = SessionStatus.PREPARE;
    protected Set<NsUser> learners = new HashSet<>();
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected boolean isRecruiting;

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionCoverImage coverImage,
        LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImage, SessionStatus.PREPARE, true, createdAt);
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate, List<SessionCoverImage> coverImages,
        LocalDateTime createdAt) {
        this(0L, startDate, endDate, coverImages, SessionStatus.PREPARE, true, createdAt);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate,
        SessionCoverImage coverImage, SessionStatus status, boolean isRecruiting, LocalDateTime createdAt) {
        this(id, startDate, endDate, coverImage, status, isRecruiting, new HashSet<>(), createdAt, null);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate,
        List<SessionCoverImage> coverImages, SessionStatus status, boolean isRecruiting, LocalDateTime createdAt) {
        this(id, startDate, endDate, coverImages, status, isRecruiting, new HashSet<>(), createdAt, null);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate,
        SessionCoverImage coverImage, SessionStatus status, boolean isRecruiting, Set<NsUser> learners,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.status = status;
        this.isRecruiting = isRecruiting;
        this.learners = learners;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate,
        List<SessionCoverImage> coverImages, SessionStatus status, boolean isRecruiting, Set<NsUser> learners,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImages = coverImages;
        this.status = status;
        this.isRecruiting = isRecruiting;
        this.learners = learners;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
        if (!isRecruiting) {
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

    public String getStatusName() {
        return status.name();
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Long getCoverImageId() {
        return coverImage.getId();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }

    public boolean isRecruiting() {
        return isRecruiting;
    }

    public List<SessionCoverImage> getCoverImages() {
        return coverImages;
    }
}
