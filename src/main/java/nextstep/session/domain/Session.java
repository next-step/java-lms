package nextstep.session.domain;

import java.time.LocalDate;
import nextstep.session.CannotEnrollException;
import nextstep.users.domain.NsUser;

public class Session {

    private Long id;
    private String title;
    private Long courseId;
    private SessionSchedule sessionSchedule;
    private SessionCoverImage coverImage;
    private SessionProgressStatus sessionProgressStatus;
    private SessionEnrollmentStatus sessionEnrollmentStatus;
    private EnrollmentPolicy enrollmentPolicy;
    private Students students;


    public Session(Long id, Long courseId, String title, SessionSchedule sessionSchedule,
        SessionProgressStatus sessionProgressStatus,
        SessionEnrollmentStatus sessionEnrollmentStatus, EnrollmentPolicy enrollmentPolicy) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionSchedule = sessionSchedule;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionEnrollmentStatus = sessionEnrollmentStatus;
        this.enrollmentPolicy = enrollmentPolicy;
        this.students = new Students();
    }

    public Session(Long id, String title, Long courseId, SessionSchedule sessionSchedule,
        SessionProgressStatus sessionProgressStatus,
        SessionEnrollmentStatus sessionEnrollmentStatus,
        SessionCoverImage coverImage,
        EnrollmentPolicy enrollmentPolicy,
        Students students) {
        this.id = id;
        this.title = title;
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.sessionSchedule = sessionSchedule;
        this.sessionProgressStatus = sessionProgressStatus;
        this.sessionEnrollmentStatus = sessionEnrollmentStatus;
        this.enrollmentPolicy = enrollmentPolicy;
        this.students = students;
    }

    public static Session createFreeSession(Long courseId, String title,
        SessionSchedule sessionSchedule,
        SessionProgressStatus sessionProgressStatus,
        SessionEnrollmentStatus sessionEnrollmentStatus) {
        return new Session(0L, courseId, title, sessionSchedule, sessionProgressStatus,
            sessionEnrollmentStatus,
            EnrollmentPolicy.createFreePolicy());
    }

    public static Session createPaidSession(Long courseId, String title,
        SessionSchedule sessionSchedule,
        SessionProgressStatus sessionProgressStatus,
        SessionEnrollmentStatus sessionEnrollmentStatus, int maxEnrollment, int fee
    ) {
        return new Session(0L, courseId, title, sessionSchedule, sessionProgressStatus,
            sessionEnrollmentStatus,
            EnrollmentPolicy.createPaidPolicy(maxEnrollment, fee));
    }

    private boolean canEnroll(int payment) {
        if (enrollmentPolicy.isCapacityFull(students.enrolledStudentCount())) {
            throw new CannotEnrollException("수강 인원 초과로 인해 현재 이 강의에 추가 등록이 불가능합니다.");
        }
        if (!sessionEnrollmentStatus.isSessionOpenForRegistration()) {
            throw new CannotEnrollException("현재 모집중인 강의가 아닙니다.");
        }
        if (!enrollmentPolicy.isPaymentCorrect(payment)) {
            throw new CannotEnrollException("수강료와 지불금액이 일치하지 않습니다.");
        }
        return true;
    }

    public Student enroll(NsUser user, int payment) {
        canEnroll(payment);
        Student student = new Student(user.getId(), id);
        students.add(student);
        return student;
    }

    public int enrolledStudentCount() {
        return students.enrolledStudentCount();
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
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
        return enrollmentPolicy.getPriceType();
    }

    public int getMaxEnrollment() {
        return enrollmentPolicy.getMaxEnrollment();
    }

    public int getFee() {
        return enrollmentPolicy.getFee();
    }

    public String getSessionEnrollmentStatus() {
        return sessionEnrollmentStatus.getDescription();
    }
}
