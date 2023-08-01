package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Session {
    private final long id;
    private final SessionManagement sessionManagement;
    private final String title;
    private CoverImage coverImage;

    public Session(long id, String title, int maxNumberOfStudent, boolean free) {
        this(id, title, maxNumberOfStudent, free, null);
    }

    public Session(long id, String title, int maxNumberOfStudent, boolean free, CoverImage coverImage) {
        this.id = id;
        this.title = title;
        this.sessionManagement = new SessionManagement(free, maxNumberOfStudent);
        this.coverImage = coverImage;
    }

    public void enrolment(NsUser user) throws IllegalArgumentException {
        sessionManagement.enrolment(user);
    }

    public SessionStatus startRecruiting() {
        return sessionManagement.startRecruiting();
    }

    public SessionStatus startSession() {
        return sessionManagement.startSession();
    }

    public SessionStatus endSession() {
        return sessionManagement.endSession();
    }

    public void addCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
    }

    public boolean isSessionWithId(long id) {
        return this.id == id;
    }
}
