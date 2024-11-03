package nextstep.courses.domain.course;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.session.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course extends BaseTime {

    private Long id;

    private String title;

    private Long creatorId;

    private Integer classNumber;

    private CoverImage coverImage;

    private List<Session> sessions;

    private Course() {
    }

    public Course(String title, Integer classNumber, Long creatorId) {
        this(0L, title, creatorId, classNumber, null, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createAt, LocalDateTime updateAt) {
        this(id, title, creatorId, 1, null, createAt, updateAt);
    }

    private Course(Long id, String title, Long creatorId, Integer classNumber, CoverImage coverImage, LocalDateTime createAt, LocalDateTime updateAt) {
        validateRequiredFields(title, creatorId, classNumber);

        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.classNumber = classNumber;
        this.coverImage = coverImage;
        this.createdAt = createAt;
        this.updatedAt = updateAt;
        sessions = new ArrayList<>();
    }

    private void validateRequiredFields(String title, Long creatorId, Integer classNumber) {
        if (isTitleNullOrEmpty(title)) {
            throw new IllegalArgumentException("제목은 필수 입력 사항입니다.");
        }
        if (isCreatorIdNull(creatorId)) {
            throw new IllegalArgumentException("작성자 아이디는 필수 입력 사항입니다.");
        }
        if (isClassNumberNull(classNumber)) {
            throw new IllegalArgumentException("기수는 필수 입력 사항입니다.");
        }
    }

    private boolean isTitleNullOrEmpty(String title) {
        return title == null || title.trim().isEmpty();
    }

    private boolean isClassNumberNull(Integer classNumber) {
        return classNumber == null;
    }

    private boolean isCreatorIdNull(Long creatorId) {
        return creatorId == null;
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
