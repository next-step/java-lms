package nextstep.courses.domain.course;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.session.Session;

import java.util.ArrayList;
import java.util.List;

public class Course extends BaseTIme {
    private Long id;

    private String title;

    private Long creatorId;

    private Integer classNumber;

    private CoverImage coverImage;

    private List<Session> sessions;

    private Course() {
    }

    private Course(Long id, String title, Long creatorId, Integer classNumber, CoverImage coverImage) {
        validateRequiredFields(title, creatorId, classNumber);

        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.classNumber = classNumber;
        this.coverImage = coverImage;
        sessions = new ArrayList<>();
    }

    public Course (String title, Integer classNumber, Long creatorId) {
        this(0L, title, creatorId, classNumber, null);
    }

    private void validateRequiredFields(String title, Long creatorId, Integer classNumber) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title은 필수 입력 사항입니다.");
        }
        if (creatorId == null) {
            throw new IllegalArgumentException("CreatorId는 필수 입력 사항입니다.");
        }
        if (classNumber == null) {
            throw new IllegalArgumentException("ClassNumber는 필수 입력 사항입니다.");
        }
    }

    public static Course of(String title, Integer classNumber, Long creatorId, CoverImage coverImage) {
        return new Course(0L, title, creatorId, classNumber, coverImage);
    }





    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
