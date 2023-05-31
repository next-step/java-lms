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



    private Course course;

    public Session(LocalDate startDate, LocalDate endDate, String coverUrl, BillType billType, Price price, long maxEnrollment, Course course) {
        this.dateTray = new DateTray(startDate, endDate);
        this.coverUrl = coverUrl;
        this.billType = billType;
        this.price = price;
        this.course = course;
        this.enrollmentContext = new SessionEnrollmentContext(maxEnrollment);
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
}
