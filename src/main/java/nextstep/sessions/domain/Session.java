package nextstep.sessions.domain;

import nextstep.students.domain.Student;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Session {

    private Long id;

    private Long courseId;

    private SessionDuration duration;

    private SessionCoverImage coverImage;

    private SessionPaymentType paymentType;

    private SessionRegistration registration;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Session(Long courseId,
                   SessionDuration duration,
                   SessionCoverImage coverImage,
                   SessionPaymentType paymentType,
                   SessionRegistration registration) {
        this(null, courseId, duration, coverImage, paymentType, registration, LocalDateTime.now(), null);
    }

    public Session(Long id,
                   Long courseId,
                   SessionDuration duration,
                   SessionCoverImage coverImage,
                   SessionPaymentType paymentType,
                   SessionRegistration registration,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.duration = duration;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.registration = registration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void register(NsUser student) {
        registration.register(student, this);
    }

    public Student enrolledStudent(NsUser student) {
        return registration.enrolledStudent(student);
    }

    public Student approved(NsUser user, List<Student> students) {
        registration.addAll(students);

        Student student = enrolledStudent(user);
        student.approved();

        return student;
    }

    public Student rejected(NsUser user, List<Student> students) {
        registration.addAll(students);

        Student student = enrolledStudent(user);
        student.rejected();

        return student;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public SessionDuration getDuration() {
        return duration;
    }

    public LocalDateTime getStartedAt() {
        return duration.getStartedAt();
    }

    public LocalDateTime getEndedAt() {
        return duration.getEndedAt();
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }

    public String getCoverImageUrl() {
        return coverImage.getImageUrl();
    }

    public SessionPaymentType getPaymentType() {
        return paymentType;
    }

    public String getPaymentTypeName() {
        return paymentType.getName();
    }

    public SessionRegistration getRegistration() {
        return registration;
    }

    public SessionStatus getStatus() {
        return registration.getStatus();
    }

    public String getStatusName() {
        return registration.getStatusName();
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return registration.getRecruitmentStatus();
    }

    public String geRecruitmentStatusName() {
        return registration.getRecruitmentStatusName();
    }

    public int getStudentCapacity() {
        return registration.getStudentCapacity();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", duration=" + duration +
                ", coverImage=" + coverImage +
                ", paymentType=" + paymentType +
                ", registration=" + registration +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
