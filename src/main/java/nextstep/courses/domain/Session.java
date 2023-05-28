package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String coverUrl;

    private Status progressStatus;

    private BillType billType;

    private Price price;

    private long maxEnrollment;

    private Course course;

    private final List<Student> students = new ArrayList<>();

    public Session(LocalDate startDate, LocalDate endDate, String coverUrl, BillType billType, Price price, long maxEnrollment, Course course) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverUrl = coverUrl;
        this.progressStatus = Status.NOT_STARTED;
        this.billType = billType;
        this.price = price;
        this.maxEnrollment = maxEnrollment;
        this.course = course;

        checkPriceValidate();
    }

    private void checkPriceValidate() {
        if (this.billType == BillType.FREE && this.price.isFree()) {
            return;
        }

        if (this.billType == BillType.PAID && this.price.isNotFree()) {
            return;
        }

        throw new IllegalArgumentException("BillType과 Price가 일치하지 않습니다.");
    }

    public boolean statusEquals(Status status) {
        return this.progressStatus == status;
    }

    public BillType getBillType() {
        return billType;
    }

    public long getNumberOfStudents() {
        return this.students.size();
    }

    public boolean isEnrollable() {
        return this.progressStatus == Status.IN_PROGRESS &&
                this.maxEnrollment > students.size();
    }

    public enum Status {
        NOT_STARTED, IN_PROGRESS, FINISHED
    }

    public enum BillType {
        FREE, PAID
    }

    public void start() {
        this.progressStatus = Status.IN_PROGRESS;
    }

    public void end() {
        this.progressStatus = Status.FINISHED;
    }

    public Student enroll(NsUser user) {
        checkEnrollmentValidate();
        Student student = new Student(this, user);
        this.students.add(student);
        return student;
    }

    private void checkEnrollmentValidate() {
        if (this.progressStatus == Status.FINISHED) {
            throw new IllegalArgumentException("강의가 종료되었습니다.");
        }

        if (this.progressStatus == Status.NOT_STARTED) {
            throw new IllegalArgumentException("아직 모집중이 아닙니다.");
        }

        if (this.maxEnrollment <= this.course.getNumberOfStudents()) {
            throw new IllegalArgumentException("수강생이 가득 찼습니다.");
        }
    }

    public Price getPrice() {
        return price;
    }
}
