package nextstep.courses.domain;

public class SessionInfo {

    private final Long courseId;
    private final Long ownerId;
    private final String title;
    private final String coverImageInfo;
    private final SessionType sessionType;

    public SessionInfo(Long courseId, Long ownerId, String title, String coverImageInfo, SessionType sessionType) {
        this.courseId = courseId;
        this.ownerId = ownerId;
        this.title = title;
        this.coverImageInfo = coverImageInfo;
        this.sessionType = sessionType;
    }
}
