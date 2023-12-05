package nextstep.courses.domain;

import nextstep.courses.enumeration.SessionStatus;
import nextstep.courses.enumeration.SessionType;
import nextstep.courses.exception.CanNotRegisterSessionException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Session extends BaseEntity {

    private final Long id;
    private final Long courseId;
    private final String title;
    protected final SessionImages sessionImages;
    protected final Students students;
    private final SessionType sessionType;
    private final SessionStatus status;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    protected Session(Long id, Long courseId, String title, SessionImages sessionImages, SessionType sessionType, SessionStatus status, LocalDateTime startAt, LocalDateTime endAt) {
        super(LocalDateTime.now(), LocalDateTime.now());
        validate(startAt, endAt);
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionImages = sessionImages;
        this.sessionType = sessionType;
        this.students = new Students(new HashSet<>());
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Session(Long id, Long courseId, String title, SessionType sessionType, Integer maxParticipants, Integer price, SessionStatus status, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        validate(startAt, endAt);
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionImages = new SessionImages(new ArrayList<>());
        this.sessionType = sessionType;
        this.students = new Students(new HashSet<>());
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validate(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt == null) {
            throw new IllegalArgumentException("강의 시작일이 없습니다.");
        }

        if (endAt == null) {
            throw new IllegalArgumentException("강의 종료일이 없습니다.");
        }
    }

    protected void isSessionRegistering() {
        if (!SessionStatus.REGISTERING.equals(this.status)) {
            throw new CanNotRegisterSessionException("강의가 모집중이여야만 신청할 수 있습니다.");
        }
    }

    public long getId() {
        return this.id;
    }

    public Set<NsUser> getStudents() {
        return this.students.getStudents();
    }

    public String getTitle() {
        return this.title;
    }

    public SessionType getSessionType() {
        return this.sessionType;
    }

    public SessionStatus getStatus() {
        return this.status;
    }

    public LocalDateTime getStartAt() {
        return this.startAt;
    }

    public LocalDateTime getEndAt() {
        return this.endAt;
    }

    public long getCourseId() {
        return this.courseId;
    }
}
