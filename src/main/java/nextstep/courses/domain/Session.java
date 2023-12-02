package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {

    private String title;
    private Course course;
    private LocalDate startDate;
    private LocalDate endDate;
    private SessionImage sessionImage;

    public Session imageOf(SessionImage sessionImage) {
        Session session = this;
        session.sessionImage = sessionImage;
        return session;
    }

    public static Session valueOf(String title, Course course, LocalDate startDate, LocalDate endDate) {
        return new Session(title, course, startDate, endDate, null);
    }

    public Session(String title) {
        this(title, null, LocalDate.now(), LocalDate.now(), null);
    }

    private Session(String title, Course course, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        this.title = title;
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = sessionImage;
    }

    public boolean hasImage() {
        return !(sessionImage == null);
    }

    public void toCourse(Course course) {
        this.course = course;
    }
}
