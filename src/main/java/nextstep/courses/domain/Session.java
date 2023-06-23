package nextstep.courses.domain;

import nextstep.courses.domain.SessionEnrollmentContext.Status;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;
    private DateTray dateTray;
    private String coverUrl;
    private BillType billType;
    private Price price;
    private SessionEnrollmentContext enrollmentContext;
    private Long courseId;

    public DateTray getDateTray() {
        return dateTray;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public SessionEnrollmentContext getEnrollmentContext() {
        return enrollmentContext;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Session(LocalDate startDate, LocalDate endDate, String coverUrl, BillType billType, Price price, long maxEnrollment, Long courseId) {
        this.dateTray = new DateTray(startDate, endDate);
        this.coverUrl = coverUrl;
        this.billType = billType;
        this.price = price;
        this.courseId = courseId;
        this.enrollmentContext = new SessionEnrollmentContext(maxEnrollment);
        checkPriceValidate();
    }

    public Session(Long id, LocalDate startDate, LocalDate endDate,
                   String coverUrl, BillType billType, Price price,
                   long maxEnrollment, Status progressStatus, Long courseId,
                   List<Student> students) {
        this(startDate, endDate, coverUrl, billType, price, maxEnrollment, courseId);

        this.id = id;
        this.enrollmentContext = new SessionEnrollmentContext(maxEnrollment, progressStatus, students);
        checkPriceValidate();
    }

    private void checkPriceValidate() {
        if (price.valid(billType)) {
            return;
        }

        throw new IllegalArgumentException("BillType과 Price가 일치하지 않습니다.");
    }

    public boolean statusEquals(Status status) {
        return this.enrollmentContext.statusEquals(status);
    }

    public BillType getBillType() {
        return billType;
    }

    public boolean isEnrollable() {
        return enrollmentContext.isEnrollable();
    }

    public enum BillType {
        FREE, PAID
    }

    public void start() {
        enrollmentContext.start();
    }

    public void end() {
        enrollmentContext.end();
    }

    public Student enroll(NsUser user) {
        Student student = new Student(this, user);
        return enrollmentContext.enroll(student);
    }

    public Price getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }
}
