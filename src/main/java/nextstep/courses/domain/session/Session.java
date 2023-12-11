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

    private final SessionType sessionType;
    private ProgressState progressState;
    private RecruitState recruitState;

    private final Period period;
    private final Long amount;
    private final Long enrollmentMax;

    private Images images;
    private Students students;

    private final Enrollment enrollment;


    public Session(Long id, SessionType sessionType, ProgressState progressState, RecruitState recruitState, Period period, Long amount, Long enrollmentMax, Images images, Students students) {
        this.id = id;
        this.sessionType = sessionType;
        this.progressState = progressState;
        this.recruitState = recruitState;
        this.period = period;
        this.amount = amount;
        this.enrollmentMax = enrollmentMax;
        this.images = images;
        this.students = students;
        this.enrollment = Enrollment.from(sessionType);
    }

    public static Session ofFree(Period period, Image image) {
        return of(null, SessionType.FREE, ProgressState.PREPARING, RecruitState.CLOSED, period, null, null, Images.of(image), Students.of(new ArrayList<>()));
    }

    public static Session ofPaid(Period period, Image image, long amount, long enrollmentMax) {
        return of(null, SessionType.PAID, ProgressState.PREPARING, RecruitState.CLOSED, period, amount, enrollmentMax, Images.of(image), Students.of(new ArrayList<>()));
    }

    public static Session of(Long id, String sessionType, String progressState, String recruitState, LocalDate startDate, LocalDate endDate, Long amount, Long enrollmentMax) {
        return of(id, SessionType.valueOf(sessionType), ProgressState.valueOf(progressState), RecruitState.valueOf(recruitState), Period.of(startDate, endDate), amount, enrollmentMax, null, null);
    }

    public static Session of(Long id, SessionType sessionType, ProgressState progressState, RecruitState recruitState, Period period, Long amount, Long enrollmentMax, Images images, Students students) {
        return new Session(id, sessionType, progressState, recruitState, period, amount, enrollmentMax, images, students);
    }

    public void enroll(NsUser nsUser, Payment payment) {
        Student student = Student.of(nsUser, SessionApproval.WAIT);
        enroll(student, payment);
    }

    public void enroll(Student student, Payment payment) {
        enrollment.enroll(this, student, payment);
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
        return students.size() < enrollmentMax;
    }

    public void preparing() {
        progressState = ProgressState.PREPARING;
    }

    public void ongoing() {
        progressState = ProgressState.ONGOING;
    }

    public void end() {
        progressState = ProgressState.END;
    }

    public void recruiting() {
        recruitState = RecruitState.RECRUITING;
    }

    public void closed() {
        recruitState = RecruitState.CLOSED;
    }

    public Long id() {
        return id;
    }

    public String type() {
        return sessionType.name();
    }

    public ProgressState progressState() {
        return progressState;
    }

    public String progressStateValue() {
        return progressState.name();
    }

    public RecruitState recruitState() {
        return recruitState;
    }

    public boolean isRecruiting() {
        return recruitState.recruiting();
    }

    public String recruitStateValue() {
        return recruitState.name();
    }

    public LocalDate startDate() {
        return period.startDate();
    }

    public LocalDate endDate() {
        return period.endDate();
    }

    public Long amount() {
        return amount;
    }

    public Long enrollmentMax() {
        return enrollmentMax;
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
