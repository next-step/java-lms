package nextstep.courses.domain;

import nextstep.courses.CannotSignUpException;

import java.time.LocalDate;

public class Session {
    private String title;
    private Course course;
    private SessionType sessionType;

    private LocalDate startDate;
    private LocalDate endDate;

    private SessionImage sessionImage;

    public static Session titleOf(String title) {
        return new Session(title, null, LocalDate.now(), LocalDate.now());
    }

    public Session imageOf(SessionImage sessionImage) {
        Session session = this;
        session.sessionImage = sessionImage;
        return session;
    }

    public Session(String title, Course course) {
        this(title, course, LocalDate.now(), LocalDate.of(9999, 12, 31));
    }

    private Session(String title, Course course, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionImage = null;
    }

    public void signUp() throws CannotSignUpException {
    }

    public void cancel() {

    }

    public boolean hasImage() {
        return !(sessionImage == null);
    }

    public void toCourse(Course course) {
        this.course = course;
    }
}
