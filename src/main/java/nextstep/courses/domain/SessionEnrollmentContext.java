package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class SessionEnrollmentContext {
    private final List<Student> students = new ArrayList<>();
    private SessionStatus progressSessionStatus = SessionStatus.NOT_STARTED;
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.ENROLLABLE;
    private long maxEnrollment;

    public SessionEnrollmentContext(long maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public SessionEnrollmentContext(long maxEnrollment, SessionStatus progressSessionStatus) {
        this(maxEnrollment);
        this.progressSessionStatus = progressSessionStatus;
    }

    public SessionEnrollmentContext(long maxEnrollment, SessionStatus progressSessionStatus, EnrollmentStatus enrollmentStatus, List<Student> students) {
        this(maxEnrollment, progressSessionStatus);
        this.enrollmentStatus = enrollmentStatus;
        this.students.addAll(students);
    }

    public boolean statusEquals(SessionStatus sessionStatus) {
        return progressSessionStatus == sessionStatus;
    }

    public long getNumberOfStudents() {
        return this.students.size();
    }

    public boolean isEnrollable() {
        return isNotFinished() && isNotFull() && isStatusEnrollable();
    }

    private boolean isNotFinished() {
        return this.progressSessionStatus != SessionStatus.FINISHED;
    }

    private boolean isNotFull() {
        return this.maxEnrollment > students.size();
    }

    private boolean isStatusEnrollable() {
        return this.enrollmentStatus == EnrollmentStatus.ENROLLABLE;
    }

    public void start() {
        this.progressSessionStatus = SessionStatus.IN_PROGRESS;
    }

    public void end() {
        this.progressSessionStatus = SessionStatus.FINISHED;
        stopEnrollment();
    }

    public void stopEnrollment() {
        this.enrollmentStatus = EnrollmentStatus.NOT_ENROLLABLE;
    }

    public void resumeEnrollment() {
        this.enrollmentStatus = EnrollmentStatus.ENROLLABLE;
    }

    private void checkEnrollmentValidate() {
        if (this.progressSessionStatus == SessionStatus.FINISHED) {
            throw new IllegalArgumentException("강의가 종료되었습니다.");
        }

        if (this.progressSessionStatus == SessionStatus.NOT_STARTED) {
            throw new IllegalArgumentException("아직 모집중이 아닙니다.");
        }

        if (this.maxEnrollment <= this.getNumberOfStudents()) {
            throw new IllegalArgumentException("수강생이 가득 찼습니다.");
        }
    }

    public Student enroll(Student student) {
        checkEnrollmentValidate();
        students.add(student);
        return student;
    }

    public enum SessionStatus {
        NOT_STARTED, IN_PROGRESS, FINISHED
    }

    public enum EnrollmentStatus {
        ENROLLABLE, NOT_ENROLLABLE
    }

    public List<Student> getStudents() {
        return List.copyOf(students);
    }

    public SessionStatus getProgressStatus() {
        return progressSessionStatus;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public long getMaxEnrollment() {
        return maxEnrollment;
    }
}
