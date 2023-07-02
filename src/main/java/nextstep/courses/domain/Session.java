package nextstep.courses.domain;

import nextstep.courses.domain.SessionEnrollmentContext.SessionStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
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

    public Session(Long id, DateTray dateTray, String coverUrl, BillType billType, Price price, SessionEnrollmentContext enrollmentContext, Long courseId) {
        this.id = id;
        this.dateTray = dateTray;
        this.coverUrl = coverUrl;
        this.billType = billType;
        this.price = price;
        this.enrollmentContext = enrollmentContext;
        this.courseId = courseId;
        checkPriceValidate();
    }

    public Session(LocalDate startDate, LocalDate endDate, String coverUrl, BillType billType, Price price, long maxEnrollment, Long courseId) {
        this(0L, new DateTray(startDate, endDate), coverUrl, billType, price, new SessionEnrollmentContext(maxEnrollment), courseId);
        checkPriceValidate();
    }

    public Session(Long id, LocalDate startDate, LocalDate endDate,
                   String coverUrl, BillType billType, Price price,
                   long maxEnrollment, SessionStatus progressSessionStatus, SessionEnrollmentContext.EnrollmentStatus enrollmentStatus,
                   Long courseId, List<Student> students) {
        this(id, new DateTray(startDate, endDate), coverUrl, billType, price, new SessionEnrollmentContext(maxEnrollment, progressSessionStatus, enrollmentStatus, students), courseId);
        checkPriceValidate();
    }

    private void checkPriceValidate() {
        if (price.valid(billType)) {
            return;
        }

        throw new IllegalArgumentException("BillType과 Price가 일치하지 않습니다.");
    }

    public boolean statusEquals(SessionStatus sessionStatus) {
        return this.enrollmentContext.statusEquals(sessionStatus);
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
