package nextstep.courses.domain.session;

import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.type.ProgressState;
import nextstep.courses.type.RecruitState;
import nextstep.courses.type.SessionApproval;
import nextstep.courses.type.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;

public class Session {
    private final Long id;

    private final SessionInfo sessionInfo;
    private final Enrollment enrollment;

    private Images images;
    private Students students;

    public Session(Long id, SessionInfo sessionInfo, Enrollment enrollment, Images images, Students students) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.enrollment = enrollment;
        this.images = images;
        this.students = students;
    }

    public static Session ofFree(Period period, Image image) {
        return of(null,
                SessionInfo.of(SessionType.FREE, period),
                Enrollment.of(ProgressState.PREPARING, RecruitState.CLOSED, null, null, SessionType.PAID),
                Images.of(image),
                Students.of(new ArrayList<>()));
    }

    public static Session ofPaid(Period period, Image image, long amount, long enrollmentMax) {
        return of(null,
                SessionInfo.of(SessionType.PAID, period),
                Enrollment.of(ProgressState.PREPARING, RecruitState.CLOSED, amount, enrollmentMax, SessionType.PAID),
                Images.of(image),
                Students.of(new ArrayList<>())
        );
    }

    public static Session of(Long id, SessionInfo sessionInfo, Enrollment enrollment, Images images, Students students) {
        return new Session(id, sessionInfo, enrollment, images, students);
    }

    public void enroll(NsUser nsUser, Payment payment) {
        Student student = Student.of(nsUser, SessionApproval.WAIT);
        enroll(student, payment);
    }

    public void enroll(Student student, Payment payment) {
        enrollment.enroll(students, student, payment);
    }

    public Student approvalSession(NsUser nsUser) {
        Student student = students.orElseThrow(nsUser);

        if (!student.isWait()) {
            throw new IllegalArgumentException("준비 상태에서만 수강승인이 가능합닌다.");
        }

        student.approval();

        return student;
    }

    public Student approvalCancel(NsUser nsUser) {
        Student student = students.orElseThrow(nsUser);

        if (!student.isWait()) {
            throw new IllegalArgumentException("준비 상태에서만 수강 취소가 가능합니다.");
        }

        student.cancel();

        return student;
    }

    public void preparing() {
        enrollment.preparing();
    }

    public void ongoing() {
        enrollment.ongoing();
    }

    public void end() {
        enrollment.end();
    }

    public void recruiting() {
        enrollment.recruiting();
    }


    public Long id() {
        return id;
    }

    public String type() {
        return sessionInfo.sessionTypeValue();
    }

    public String progressStateValue() {
        return enrollment.progressStateValue();
    }

    public String recruitStateValue() {
        return enrollment.recruitStateValue();
    }

    public LocalDate startDate() {
        return sessionInfo.startDate();
    }

    public LocalDate endDate() {
        return sessionInfo.endDate();
    }

    public Long amount() {
        return enrollment.amount();
    }

    public Long enrollmentMax() {
        return enrollment.enrollmentMax();
    }

    public Images images() {
        return images;
    }

    public void changeImages(Images images) {
        this.images = images;
    }

    public Students students() {
        return students;
    }

    public void changeStudents(Students students) {
        this.students = students;
    }
}
