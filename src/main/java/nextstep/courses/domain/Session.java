package nextstep.courses.domain;

import nextstep.courses.repository.EnrollmentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Session {
    private final Long id;
    private final SessionPeriod period;
    private final SessionProgressStatus progressStatus;
    private final SessionRecruitingStatus recruitingStatus;
    private final EnrollmentRule enrollmentRule;
    private ImageFiles imageFiles;
    private Enrollments enrollments;

    public Session(Long id, LocalDateTime startTime, LocalDateTime endTime, String recruitingStatus, String progressStatus, String sessionType ,Integer price, Integer capacity) {
        this(id,new ImageFiles(), new SessionPeriod(startTime, endTime), SessionRecruitingStatus.valueOf(recruitingStatus), SessionProgressStatus.valueOf(progressStatus), allocateEnrollmentRule(sessionType, price, capacity), new Enrollments());
    }

    public Session( SessionPeriod period, SessionRecruitingStatus recruitingStatus, SessionProgressStatus progressStatus, EnrollmentRule enrollmentRule) {
        this(null, new ImageFiles(), period, recruitingStatus, progressStatus, enrollmentRule, new Enrollments());
    }

    public Session(Long id, ImageFiles imageFiles, SessionPeriod period, SessionRecruitingStatus recruitingStatus, SessionProgressStatus progressStatus, EnrollmentRule enrollmentRule, Enrollments enrollments) {
        this.id = id;
        this.imageFiles = imageFiles;
        this.period = period;
        this.progressStatus = progressStatus;
        this.recruitingStatus = recruitingStatus;
        this.enrollmentRule = enrollmentRule;
        this.enrollments = enrollments;
    }

    public int countEnrollments() {
        return enrollments.countEnrollments();
    }

    public void enroll(Enrollment enrollment, Money money) {
        validationStatus();

        enrollment.validateBelongsTo(getId());

        enrollmentRule.validate(money, countEnrollments());

        enrollments.enroll(enrollment);
    }

    public Enrollment selectEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollments.findById(enrollmentId);
        return enrollment.select();
    }

    public Enrollment approveEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollments.findById(enrollmentId);
        return enrollment.approve();
    }

    public Enrollment cancelEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollments.findById(enrollmentId);
        return enrollment.cancel();
    }

    public Long getId() {
        return id;
    }

    public Long getImageId() {
        return this.imageFiles.getMainImageId();
    }

    public String getProgressStatus() {
        return this.progressStatus.toString();
    }

    public String getRecruitingStatus() {
        return this.recruitingStatus.toString();
    }

    public Integer getPrice() {
        if (enrollmentRule.getType().isPaid()) {
            return ((PaidEnrollmentRule) this.enrollmentRule).getPrice();
        }

        return 0;
    }

    public Integer getCapacity() {
        if (enrollmentRule.getType().isPaid()) {
            return ((PaidEnrollmentRule) this.enrollmentRule).getCapacity();
        }

        return Integer.MAX_VALUE;
    }

    public LocalDateTime getStartTime() {
        return this.period.getStartTime();
    }

    public LocalDateTime getEndTime() {
        return this.period.getEndTime();
    }

    public SessionPeriod getPeriod() {
        return this.period;
    }

    public String getType() {
        return this.enrollmentRule.getType().toString();
    }

    public void addImageFile(ImageFile imageFile) {
        this.imageFiles.addImage(imageFile);
        imageFile.assignSessionId(this.getId());
    }

    public ImageFiles getImageFiles() {
        return this.imageFiles;
    }

    public void loadImageFiles(List<ImageFile> imageFiles) {
        this.imageFiles = new ImageFiles(imageFiles);
    }

    public void loadEnrollments(List<Enrollment> enrollments) {
        this.enrollments = new Enrollments(enrollments);
    }

    private static EnrollmentRule allocateEnrollmentRule(String sessionType, Integer price, Integer capacity) {
        if (SessionType.from(sessionType).isPaid()) {
            return new PaidEnrollmentRule(price, capacity);
        }

        return new FreeEnrollmentRule();
    }

    private void validationStatus() {
        if (!recruitingStatus.enableRecruiting()) {
            throw new IllegalArgumentException("모집중인 강의만 수강 신청할 수 있습니다.");
        }

        if (!progressStatus.enableProgress()) {
            throw new IllegalArgumentException("종료된 강의는 수강 신청할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(imageFiles, session.imageFiles) && Objects.equals(period, session.period) && progressStatus == session.progressStatus && recruitingStatus == session.recruitingStatus && Objects.equals(enrollmentRule, session.enrollmentRule) && Objects.equals(enrollments, session.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageFiles, period, progressStatus, recruitingStatus, enrollmentRule, enrollments);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", imageFiles=" + imageFiles +
                ", period=" + period +
                ", progressStatus=" + progressStatus +
                ", recruitingStatus=" + recruitingStatus +
                ", enrollmentRule=" + enrollmentRule +
                ", enrollments=" + enrollments +
                '}';
    }
}
