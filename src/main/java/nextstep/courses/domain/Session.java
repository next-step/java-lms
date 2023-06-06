package nextstep.courses.domain;

import nextstep.courses.exception.CannotRegisterException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session extends BaseEntity {
    private final Long courseId;
    private final CoverImage coverImage;
    private final SessionPeriod period;
    private final SessionUsers users;
    private final String title;
    private final SessionStatus status;
    private final SessionType type;

    public Session(Long courseId, String title, CoverImage coverImage, SessionPeriod period, SessionType type,
                   SessionStatus status, int capacity) {
        this(0L, courseId, title, coverImage, period, type, status, capacity, LocalDateTime.now(), null);
    }

    public Session(Long id, Long courseId, String title, CoverImage coverImage, SessionPeriod period, SessionType type,
                   SessionStatus status, int capacity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.courseId = Objects.requireNonNull(courseId);
        this.title = Objects.requireNonNull(title);
        this.coverImage = Objects.requireNonNull(coverImage);
        this.period = Objects.requireNonNull(period);
        this.type = Objects.requireNonNull(type);
        this.status = Objects.requireNonNull(status);
        this.users = new SessionUsers(capacity);
    }

    public Long getCourseId() {
        return courseId;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public SessionUsers getUsers() {
        return users;
    }

    public String getTitle() {
        return title;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public SessionType getType() {
        return type;
    }

    public int getCapacity() {
        return users.capacity();
    }

    public void register(NsUser user) {
        if (!status.isRecruiting()) {
            throw new CannotRegisterException("현재 모집중인 강의가 아닙니다");
        }
        users.add(user);
    }

    @Override
    public String toString() {
        return "Session{" +
                "courseId=" + courseId +
                ", coverImage=" + coverImage +
                ", period=" + period +
                ", users=" + users +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}'
                + super.toString();
    }
}
