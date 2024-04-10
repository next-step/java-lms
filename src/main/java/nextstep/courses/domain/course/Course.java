package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import nextstep.courses.domain.lecture.Lecture;
import nextstep.courses.domain.lecture.LectureName;
import nextstep.courses.domain.lecture.Lectures;
import nextstep.payments.domain.Payment;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private Lectures lectures;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, new Lectures(), LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, Lectures lectures, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.lectures = lectures;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public boolean registerLecture(LectureName lectureName, Payment payment) {
        Lecture lecture = lectures.findLecture(lectureName);

        if (lecture.isRegistrationAvailable() && lecture.isPaymentAmountSameTuitionFee(payment)) {
            lecture.addRegistrationCount();
            return true;
        }

        return false;
    }

    public void addLecture(LectureName lectureName, Lecture lecture) {
        lectures.addLecture(lectureName,lecture);
    }
}
