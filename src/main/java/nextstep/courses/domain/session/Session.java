package nextstep.courses.domain.session;

import nextstep.courses.domain.session.enrollment2.EnrollmentInfo;
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
    private final EnrollmentInfo enrollmentInfo;

    private Images images;
    private Students students;

    public Session(Long id, SessionInfo sessionInfo, EnrollmentInfo enrollmentInfo, Images images, Students students) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.enrollmentInfo = enrollmentInfo;
        this.images = images;
        this.students = students;
    }

    public static Session ofFree(Period period, Image image) {
        return of(null,
                SessionInfo.of(SessionType.FREE, period),
                EnrollmentInfo.of(ProgressState.PREPARING, RecruitState.CLOSED, null, null, SessionType.PAID),
                Images.of(image),
                Students.of(new ArrayList<>()));
    }

    public static Session ofPaid(Period period, Image image, long amount, long enrollmentMax) {
        return of(null,
                SessionInfo.of(SessionType.PAID, period),
                EnrollmentInfo.of(ProgressState.PREPARING, RecruitState.CLOSED, amount, enrollmentMax, SessionType.PAID),
                Images.of(image),
                Students.of(new ArrayList<>())
        );
    }

    public static Session of(Long id, SessionInfo sessionInfo, EnrollmentInfo enrollmentInfo, Images images, Students students) {
        return new Session(id, sessionInfo, enrollmentInfo, images, students);
    }

    public void enroll(NsUser nsUser, Payment payment) {
        Student student = Student.of(nsUser, SessionApproval.WAIT);
        enroll(student, payment);
    }

    public void enroll(Student student, Payment payment) {
        enrollmentInfo.enroll2(students, student, payment);
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

    public boolean isFullEnrollment() {
        return students.size() < enrollmentInfo.enrollmentMax();
    }

    public void preparing() {
        enrollmentInfo.preparing();
    }

    public void ongoing() {
        enrollmentInfo.ongoing();
    }

    public void end() {
        enrollmentInfo.end();
    }

    public void recruiting() {
        enrollmentInfo.recruiting();
    }


    public Long id() {
        return id;
    }

    public String type() {
        return sessionInfo.sessionTypeValue();
    }

    public ProgressState progressState() {
        return enrollmentInfo.progressState();
    }

    public String progressStateValue() {
        return enrollmentInfo.progressStateValue();
    }

    public RecruitState recruitState() {
        return enrollmentInfo.recruitState();
    }

    public String recruitStateValue() {
        return enrollmentInfo.recruitStateValue();
    }

    public LocalDate startDate() {
        return sessionInfo.startDate();
    }

    public LocalDate endDate() {
        return sessionInfo.endDate();
    }

    public Long amount() {
        return enrollmentInfo.amount();
    }

    public Long enrollmentMax() {
        return enrollmentInfo.enrollmentMax();
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

    public void addStudent(Student student) {
        students.add(student);
    }
}
