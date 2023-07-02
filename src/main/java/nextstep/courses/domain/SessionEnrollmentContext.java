package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SessionEnrollmentContext {
    private final List<Student> students = new ArrayList<>();
    private SessionStatus progressSessionStatus = SessionStatus.NOT_STARTED;
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.ENROLLABLE;
    private long maxEnrollment;

    public SessionEnrollmentContext(long maxEnrollment) {
        this(maxEnrollment, SessionStatus.NOT_STARTED);
    }

    public SessionEnrollmentContext(long maxEnrollment, SessionStatus progressSessionStatus) {
        this(maxEnrollment, progressSessionStatus, EnrollmentStatus.ENROLLABLE, new ArrayList<>());
    }

    public SessionEnrollmentContext(long maxEnrollment, SessionStatus progressSessionStatus, EnrollmentStatus enrollmentStatus, List<Student> students) {
        this.maxEnrollment = maxEnrollment;
        this.progressSessionStatus = progressSessionStatus;
        this.enrollmentStatus = enrollmentStatus;
        this.students.addAll(students);
    }

    public boolean statusEquals(SessionStatus sessionStatus) {
        return progressSessionStatus == sessionStatus;
    }

    public long getNumberOfStudents() {
        return getEnrolledStudents().size();
    }

    public boolean isEnrollable() {
        return progressSessionStatus.isNotFinished() && isNotFull() && isStatusEnrollable();
    }

    private List<Student> getEnrolledStudents() {
        return this.students.stream()
                .filter(student -> student.getStatus().isEnrolled())
                .collect(Collectors.toList());
    }

    private boolean isNotFull() {
        return this.maxEnrollment > getEnrolledStudents().size();
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
        if (!progressSessionStatus.isNotFinished()) {
            throw new IllegalArgumentException("강의가 종료되었습니다.");
        }

        if (this.progressSessionStatus.isNotStarted()) {
            throw new IllegalArgumentException("아직 모집중이 아닙니다.");
        }

        if (!isNotFull()) {
            throw new IllegalArgumentException("수강생이 가득 찼습니다.");
        }
    }

    public Student requestEnroll(Student student) {
        checkEnrollmentValidate();
        students.add(student);
        return student;
    }

    public enum SessionStatus {
        NOT_STARTED, IN_PROGRESS, FINISHED;

        public boolean isNotFinished() {
            return this != FINISHED;
        }

        public boolean isNotStarted() {
            return this == NOT_STARTED;
        }
    }

    public enum EnrollmentStatus {
        ENROLLABLE, NOT_ENROLLABLE;

        public boolean isEnrollable() {
            return this == ENROLLABLE;
        }
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
