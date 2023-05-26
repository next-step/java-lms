package nextstep.courses.domain;

public class SessionBuilder {
    private Long id;
    private SessionBillingType sessionBillingType;
    private SessionStatusType sessionStatusType;
    private SessionCoverImage sessionCoverImage;
    private int maxUserCount;
    private SessionPeriod sessionPeriod;

    private SessionBuilder() {
    }

    public static SessionBuilder init() {
        return new SessionBuilder();
    }

    public SessionBuilder maxUserCount(int maxUserCount) {
        this.maxUserCount = maxUserCount;
        return this;
    }

    public SessionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder sessionBillType(SessionBillingType sessionBillingType) {
        this.sessionBillingType = sessionBillingType;
        return this;
    }

    public SessionBuilder sessionStatus(SessionStatusType sessionStatusType) {
        this.sessionStatusType = sessionStatusType;
        return this;
    }

    public SessionBuilder sessionCoverImage(SessionCoverImage sessionCoverImage) {
        this.sessionCoverImage = sessionCoverImage;
        return this;
    }

    public SessionBuilder sessionPeriod(SessionPeriod sessionPeriod) {
        this.sessionPeriod = sessionPeriod;
        return this;
    }

    public Session build() {
        return new Session(maxUserCount, id, sessionBillingType, sessionStatusType, sessionCoverImage, sessionPeriod);
    }

}
