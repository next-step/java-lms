package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public final class CourseBuilder {
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

    private CourseBuilder() {
    }

    public static CourseBuilder aCourse() {
        return new CourseBuilder();
    }

    public CourseBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CourseBuilder withSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    public CourseBuilder withUsers(List<NsUser> users) {
        this.users = users;
        return this;
    }

    public CourseBuilder withCourseType(CourseType courseType) {
        this.courseType = courseType;
        return this;
    }

    public CourseBuilder withCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
        return this;
    }

    public CourseBuilder withCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
        return this;
    }

    public CourseBuilder withMaxUserCount(int maxUserCount) {
        this.maxUserCount = maxUserCount;
        return this;
    }

    public CourseBuilder withStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    public CourseBuilder withEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
        return this;
    }

    public CourseBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CourseBuilder withCreatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public CourseBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CourseBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Course build() {
        return new Course(id, users, sessions, courseType, courseStatus, coverImageUrl, maxUserCount, startedAt, endedAt, title, creatorId, createdAt, updatedAt);
    }
}
