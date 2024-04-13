package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.session.CannotEnrollException;
import nextstep.session.InvalidEnrollmentPolicyException;

public class Session {

    private final Long id;
    private final String title;
    private final SessionSchedule sessionSchedule;
    private final SessionCoverImage coverImage;
    private final SessionStatus sessionStatus;
    private final EnrollmentPolicy enrollmentPolicy;
    private final List<Student> students;

    public static Session createFreeSession(Long id, String title, SessionSchedule sessionSchedule,
        SessionCoverImage coverImage, SessionStatus sessionStatus)
        throws InvalidEnrollmentPolicyException {
        return new Session(id, title, sessionSchedule, coverImage, sessionStatus,
            EnrollmentPolicy.createFreeSession());
    }

    public static Session createPaidSession(Long id, String title, SessionSchedule sessionSchedule,
        SessionCoverImage coverImage, SessionStatus sessionStatus, int maxEnrollment, int fee)
        throws InvalidEnrollmentPolicyException {
        return new Session(id, title, sessionSchedule, coverImage, sessionStatus,
            EnrollmentPolicy.createPaidSession(maxEnrollment, fee));
    }

    private Session(Long id, String title, SessionSchedule sessionSchedule,
        SessionCoverImage coverImage,
        SessionStatus sessionStatus, EnrollmentPolicy enrollmentPolicy) {
        this.id = id;
        this.title = title;
        this.sessionSchedule = sessionSchedule;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.enrollmentPolicy = enrollmentPolicy;
        this.students = new ArrayList<>();
    }

    public int enrolledStudentCount() {
        return students.size();
    }

    public List<Student> getStudents() {
        return students;
    }

    private void canEnroll(int payment) throws CannotEnrollException {
        if (enrollmentPolicy.isCapacityFull(enrolledStudentCount())) {
            throw new CannotEnrollException("수강 인원 초과로 인해 현재 이 강의에 추가 등록이 불가능합니다.");
        }
        if (!sessionStatus.isSessionRecruiting()) {
            throw new CannotEnrollException("현재 모집중인 강의가 아닙니다.");
        }
        if (!enrollmentPolicy.isPaymentCorrect(payment)) {
            throw new CannotEnrollException("수강료와 지불금액이 일치하지 않습니다.");
        }
    }

    public void enroll(Student student, int payment) throws CannotEnrollException {
        canEnroll(payment);
        this.students.add(student);

    }


}
