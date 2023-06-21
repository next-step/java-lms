package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Session {
    private Long id;

    private String title;

    private Period period;

    private String coverImageUrl;

    private Boolean isFree;

    private SessionStatus sessionStatus;

    private Registration registration;

    public Session(Long id, String title, Period period, String coverImageUrl, Boolean isFree, SessionStatus sessionStatus, Registration registration) {
        this.id = id;
        this.title = title;
        this.period = period;
        this.coverImageUrl = coverImageUrl;
        this.isFree = isFree;
        this.sessionStatus = sessionStatus;
        this.registration = registration;
    }

    public Session(Session session, List<NsUser> students) {
        this.id = session.id;
        this.title = session.title;
        this.period = session.period;
        this.coverImageUrl = session.coverImageUrl;
        this.isFree = session.isFree;
        this.sessionStatus = session.sessionStatus;
        this.registration = new Registration(session.registration, students);
    }

    public Student add(NsUser user) {
        return new Student(this.id, registration.register(user));
    }

    public String getTitle() {
        return title;
    }
}
