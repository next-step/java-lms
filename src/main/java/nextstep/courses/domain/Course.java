package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {
    private CourseId courseId;

    private String title;

    private Long creatorId;

    private List<Term> terms;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long courseId, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.courseId = new CourseId(courseId);
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return this.creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public boolean includeSession(Session session) {
        return this.terms
                .stream()
                .anyMatch(term -> term.includeSession(session));
    }

    public void establishTerm(Term term) {
        this.terms.add(term);
    }
}
