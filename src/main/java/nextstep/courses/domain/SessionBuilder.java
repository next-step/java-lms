package nextstep.courses.domain;

public class SessionBuilder {

    private Long id;

    private SessionPeriod sessionPeriod;

    private String coverImage;

    private boolean isFree;

    private SessionStatus sessionStatus;

    private SessionPersonnel sessionPersonnel;

    private SessionBuilder() {
    }

    public static SessionBuilder aSession() {
        return new SessionBuilder();
    }

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withSessionPeriod(SessionPeriod sessionPeriod) {
        this.sessionPeriod = sessionPeriod;
        return this;
    }

    public SessionBuilder withCoverImage(String coverImage) {
        this.coverImage = coverImage;
        return this;
    }


    public SessionBuilder withIsFree(boolean isFree) {
        this.isFree = isFree;
        return this;
    }

    public SessionBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }

    public SessionBuilder withSessionPersonnel(SessionPersonnel sessionPersonnel) {
        this.sessionPersonnel = sessionPersonnel;
        return this;
    }

    public Session build() {
        return new Session(id, sessionPeriod, coverImage, isFree, sessionStatus, sessionPersonnel);
    }

}
