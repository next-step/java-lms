package nextstep.session.domain;

import nextstep.session.CannotEnrollException;
import nextstep.session.InvalidEnrollmentPolicyException;
import nextstep.session.StudentAlreadyEnrolledException;

public class Session {

    private final Long id;
    private final String title;
    private final Long courseId;
    private final SessionSchedule sessionSchedule;
    private final SessionCoverImage coverImage;
    private final SessionStatus sessionStatus;
    private final EnrollmentPolicy enrollmentPolicy;
    private final Students students;

    public static Session createFreeSession(Long id, Long courseId, String title,
        SessionSchedule sessionSchedule,
        SessionCoverImage coverImage, SessionStatus sessionStatus)
        throws InvalidEnrollmentPolicyException {
        return new Session(id, courseId, title, sessionSchedule, coverImage, sessionStatus,
            EnrollmentPolicy.createFreePolicy());
    }

    public static Session createPaidSession(Long id, Long courseId, String title,
        SessionSchedule sessionSchedule,
        SessionCoverImage coverImage, SessionStatus sessionStatus, int maxEnrollment, int fee)
        throws InvalidEnrollmentPolicyException {
        return new Session(id, courseId, title, sessionSchedule, coverImage, sessionStatus,
            EnrollmentPolicy.createPaidPolicy(maxEnrollment, fee));
    }

    private Session(Long id, Long courseId, String title, SessionSchedule sessionSchedule,
        SessionCoverImage coverImage,
        SessionStatus sessionStatus, EnrollmentPolicy enrollmentPolicy) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.sessionSchedule = sessionSchedule;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.enrollmentPolicy = enrollmentPolicy;
        this.students = new Students();
    }

    private boolean canEnroll(int payment) throws CannotEnrollException {
        if (enrollmentPolicy.isCapacityFull(students.enrolledStudentCount())) {
            throw new CannotEnrollException("수강 인원 초과로 인해 현재 이 강의에 추가 등록이 불가능합니다.");
        }
        if (!sessionStatus.isSessionRecruiting()) {
            throw new CannotEnrollException("현재 모집중인 강의가 아닙니다.");
        }
        if (!enrollmentPolicy.isPaymentCorrect(payment)) {
            throw new CannotEnrollException("수강료와 지불금액이 일치하지 않습니다.");
        }
        return true;
    }

    public void enroll(Student student, int payment)
        throws CannotEnrollException, StudentAlreadyEnrolledException {
        canEnroll(payment);
        students.add(student);

    }

    public int enrolledStudentCount() {
        return students.enrolledStudentCount();
    }


}
