package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class SessionEnrollmentContext {
    private final List<Student> students = new ArrayList<>();
    private Status progressStatus = Status.NOT_STARTED;
    private long maxEnrollment;

    public SessionEnrollmentContext(long maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public SessionEnrollmentContext(long maxEnrollment, Status progressStatus) {
        this(maxEnrollment);
        this.progressStatus = progressStatus;
    }

    public SessionEnrollmentContext(long maxEnrollment, Status progressStatus, List<Student> students) {
        this(maxEnrollment, progressStatus);
        this.students.addAll(students);
    }

    public boolean statusEquals(Status status) {
        return progressStatus == status;
    }

    public long getNumberOfStudents() {
        return this.students.size();
    }

    public boolean isEnrollable() {
        return this.progressStatus == Status.IN_PROGRESS &&
                this.maxEnrollment > students.size();
    }

    public void start() {
        this.progressStatus = Status.IN_PROGRESS;
    }

    public void end() {
        this.progressStatus = Status.FINISHED;
    }

    private void checkEnrollmentValidate() {
        if (this.progressStatus == Status.FINISHED) {
            throw new IllegalArgumentException("강의가 종료되었습니다.");
        }

        if (this.progressStatus == Status.NOT_STARTED) {
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

    public enum Status {
        NOT_STARTED, IN_PROGRESS, FINISHED
    }

    public List<Student> getStudents() {
        return List.copyOf(students);
    }

    public Status getProgressStatus() {
        return progressStatus;
    }

    public long getMaxEnrollment() {
        return maxEnrollment;
    }
}
