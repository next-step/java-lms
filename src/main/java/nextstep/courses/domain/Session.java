package nextstep.courses.domain;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private static final String DATE_ERROR_MESSAGE = "시작일은 종료일 보다 늦을 수 없습니다.";
    private static final String SIGN_UP_HISTORIES_SIZE_ERROR_MESSAGE = "최대 수강 인원을 초과할 수 없습니다.";
    private static final String STATUS_ERROR_MESSAGE = "강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.";

    private Long id;

    private Course course;

    private int generation;

    private String coverImage;

    private SessionType type;

    private SessionStatus status;

    private int headCount;

    private List<SignUpHistory> signUpHistories = new ArrayList<>();

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

    public Session(long id, Long course_id, int generation, String cover_image, SessionType type, SessionStatus status, int head_count, LocalDateTime start_at, LocalDateTime end_at) {
        this(id, new Course(course_id), generation, cover_image, type, status, head_count, start_at, end_at);
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

    public List<SignUpHistory> getSignUpHistories() {
        return signUpHistories;
    }

    public void addSignUpHistory(SignUpHistory signUpHistory) {
        validStatus();
        signUpHistory.toSession(this);
        signUpHistories.add(signUpHistory);
        validSignUpHistoriesSize();
    }

    private void validStatus() {
        if (SessionStatus.RECRUIT != this.status) {
            throw new IllegalArgumentException(STATUS_ERROR_MESSAGE);
        }
    }

    private void validSignUpHistoriesSize() {
        if (this.signUpHistories.size() > headCount) {
            throw new IllegalArgumentException(SIGN_UP_HISTORIES_SIZE_ERROR_MESSAGE);
        }
    }

    public void toCourse(Course course) {
        this.course = course;
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
