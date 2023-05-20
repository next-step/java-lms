package nextstep.courses.domain;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;

import java.time.LocalDateTime;

public class Session {

    private static final String DATE_ERROR_MESSAGE = "시작일은 종료일 보다 늦을 수 없습니다.";

    private Long id;

    private Course course;

    private int generation;

    private String coverImage;

    private SessionType type;

    private SessionStatus status;

    private int headCount;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public Session(Course course, int generation, String coverImage, SessionType type, SessionStatus status, int headCount, LocalDateTime startAt, LocalDateTime endAt) {
        this(0L, course, generation, coverImage, type, status, headCount, startAt, endAt);
    }

    public Session(Long id, Course course, int generation, String coverImage, SessionType type, SessionStatus status, int headCount, LocalDateTime startAt, LocalDateTime endAt) {
        validDate(startAt, endAt);
        this.id = id;
        this.course = course;
        this.generation = generation;
        this.coverImage = coverImage;
        this.type = type;
        this.status = status;
        this.headCount = headCount;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private static void validDate(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException(DATE_ERROR_MESSAGE);
        }
    }

    public Course getCourse() {
        return course;
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
                ", course=" + course +
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
