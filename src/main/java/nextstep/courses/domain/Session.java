package nextstep.courses.domain;

import nextstep.courses.domain.base.BaseEntity;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Student;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session extends BaseEntity {
    private final Long courseId;
    private final CoverImage coverImage;
    private final SessionPeriod period;
    private final Enrollment enrollment;
    private final String title;

    public Session(Long courseId, String title, CoverImage coverImage, SessionPeriod period, Enrollment enrollment) {
        this(0L, courseId, title, coverImage, period, enrollment, LocalDateTime.now(), null);
    }


    public Session(Long id, Long courseId, String title, CoverImage coverImage, SessionPeriod period, Enrollment enrollment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.courseId = courseId;
        this.title = title;
        this.coverImage = coverImage;
        this.period = period;
        this.enrollment = enrollment;
    }

    public Long getCourseId() {
        return courseId;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }


    public String getTitle() {
        return title;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public Student enroll(NsUser user) {
        Student student = new Student(getId(), user.getId());
        enrollment.enroll(student);
        return student;
    }

    @Override
    public String toString() {
        return "Session{" +
                "courseId=" + courseId +
                ", coverImage=" + coverImage +
                ", enrollment=" + enrollment +
                ", title='" + title + '\'' +
                '}' + super.toString();
    }
}
