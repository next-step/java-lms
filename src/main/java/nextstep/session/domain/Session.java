package nextstep.session.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;
    private Long userId;
    private String title;
    private Long courseId;
    private SessionSchedule sessionSchedule;
    private List<SessionCoverImage> coverImages;
    private SessionProgressStatus sessionProgressStatus;
    private SessionEnrollment sessionEnrollment;

    public Session(Long id, Long userId, String title, Long courseId,
        SessionSchedule sessionSchedule,
        List<SessionCoverImage> coverImages, SessionProgressStatus sessionProgressStatus,
        SessionEnrollment sessionEnrollment) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.courseId = courseId;
        this.sessionSchedule = sessionSchedule;
        this.coverImages = coverImages;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionEnrollment = sessionEnrollment;
    }

    public Session(Long userId, String title, Long courseId, SessionSchedule sessionSchedule,
        SessionProgressStatus sessionProgressStatus,
        EnrollmentStatus enrollmentStatus, EnrollmentPolicy enrollmentPolicy) {
        this(0L, userId, title, courseId, sessionSchedule, new ArrayList<>(), sessionProgressStatus,
            new SessionEnrollment(enrollmentStatus, enrollmentPolicy, new Students()));
    }

    public static Session createSessionWithProgressStatusAndFee(SessionProgressStatus status,
        int fee) {
        return new Session(0L, "기초강의", 0L,
            new SessionSchedule(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)), status,
            EnrollmentStatus.OPEN, new EnrollmentPolicy("PAID", 100, fee));
    }


    public void addCoverImage(SessionCoverImage coverImage) {
        coverImages.add(coverImage);
    }

    public Student enroll(NsUser user, int payment) {
        return this.sessionEnrollment.enroll(user, payment, id);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return id;
    }

    public List<SessionCoverImage> getCoverImages() {
        return coverImages;
    }

    public int countCoverImages() {
        return coverImages.size();
    }

    public String getTitle() {
        return title;
    }

    public Long getCourseId() {
        return courseId;
    }

    public LocalDate getStartDate() {
        return sessionSchedule.getStartDate();
    }

    public LocalDate getEndDate() {
        return sessionSchedule.getEndDate();
    }

    public String getSessionProgressStatus() {
        return sessionProgressStatus.getDescription();
    }

    public String getPriceType() {
        return sessionEnrollment.getPriceType();
    }

    public int getMaxEnrollment() {
        return sessionEnrollment.getMaxEnrollment();
    }

    public int getFee() {
        return sessionEnrollment.getFee();
    }

    public String getSessionEnrollmentStatus() {
        return sessionEnrollment.getEnrollmentStatus();
    }

    public void approvalStudent(Student student) {
        this.sessionEnrollment.approvalStudent(student);
    }

    public void cancelStudent(Student student) {
        this.sessionEnrollment.cancelStudent(student);
    }

    public int enrolledStudentCount() {
        return this.sessionEnrollment.enrolledStudentCount();
    }

    public void changeEnrollmentStatusOpen() {
        this.sessionEnrollment.changeEnrollmentStatusOpen();
    }

    public void changeEnrollmentStatusClosed() {
        this.sessionEnrollment.changeEnrollmentStatusClosed();
    }

    public void isAuthorizedForSession(NsUser user) {
        if (userId != user.getId()) {
            throw new IllegalArgumentException("해당 강의에 권한이 없습니다.");
        }
    }
}
