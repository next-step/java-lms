package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private Long id;

    private List<Session> sessions;

    private List<NsUser> users;

    private CourseType courseType;

    private CourseStatus courseStatus;

    private String coverImageUrl;

    private int maxUserCount;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, new ArrayList<>(), new ArrayList<>(), null, CourseStatus.READY, null, 0, null,
             null, title, creatorId, createdAt, updatedAt);
    }

    public Course(Long id, List<NsUser> users, List<Session> sessions, CourseType courseType, CourseStatus courseStatus, String coverImageUrl,
                  int maxUserCount, LocalDateTime startedAt, LocalDateTime endedAt, String title, Long creatorId,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.users = users;
        this.sessions = sessions;
        this.courseType = courseType;
        this.courseStatus = courseStatus;
        this.coverImageUrl = coverImageUrl;
        this.maxUserCount = maxUserCount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public List<NsUser> getUsers() {
        return users;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", title='" + title + '\'' + ", creatorId=" + creatorId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

    public void register(NsUser user) {
        if (!this.courseStatus.isOpen()) {
            throw new IllegalArgumentException("수강신청은 모집중일때만 등록이 가능합니다.");
        }

        if (maxUserCount <= users.size()) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }

        users.add(user);
    }
}
