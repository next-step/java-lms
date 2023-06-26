package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Collections;

public class SessionBuilder {
    private Long id = 0L;
    private DateTray dateTray = new DateTray(LocalDate.now(), LocalDate.now().plusDays(1L));
    private String coverUrl = "coverUrl";
    private Session.BillType billType = Session.BillType.FREE;
    private Price price = Price.FREE;
    private SessionEnrollmentContextBuilder enrollmentContextBuilder = SessionEnrollmentContextBuilder.builder();
    private Long courseId = 0L;

    public static SessionBuilder builder() {
        return new SessionBuilder();
    }

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withDateTray(DateTray dateTray) {
        this.dateTray = dateTray;
        return this;
    }

    public SessionBuilder withCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public SessionBuilder withBillType(Session.BillType billType) {
        this.billType = billType;
        return this;
    }

    public SessionBuilder withPrice(Price price) {
        this.price = price;
        return this;
    }

    public SessionBuilder withEnrollmentContext(SessionEnrollmentContextBuilder enrollmentContextBuilder) {
        this.enrollmentContextBuilder = enrollmentContextBuilder;
        return this;
    }

    public SessionBuilder withCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public Session build() {
        return new Session(id, dateTray, coverUrl, billType, price, enrollmentContextBuilder.build(), courseId);
    }
}
