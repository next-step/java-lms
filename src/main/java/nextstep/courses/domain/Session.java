package nextstep.courses.domain;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private Long courseId;

    private int generation;

    private String coverImage;

    private SessionType type;

    private SessionStatus status;

    private int headCount;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public Session(Long courseId, int generation, String coverImage, SessionType type, SessionStatus status, int headCount, LocalDateTime startAt, LocalDateTime endAt) {
        this(0L, courseId, generation, coverImage, type, status, headCount, startAt, endAt);
    }

    public Session(Long id, Long courseId, int generation, String coverImage, SessionType type, SessionStatus status, int headCount, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.courseId = courseId;
        this.generation = generation;
        this.coverImage = coverImage;
        this.type = type;
        this.status = status;
        this.headCount = headCount;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Long getCourseId() {
        return courseId;
    }

    public int getGeneration() {
        return generation;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public SessionType getType() {
        return type;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public int getHeadCount() {
        return headCount;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", generation=" + generation +
                ", coverImage='" + coverImage + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", headCount=" + headCount +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                '}';
    }

}
