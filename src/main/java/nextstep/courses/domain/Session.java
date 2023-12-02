package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Session {
    private final Integer sessionId;
    private final String sessionName;
    private final Period sessionPeriod;
    private final Thumbnail thumbnail;
    private final SessionType sessionType;
    private final SessionStatus sessionStatus;
    private final List<NsUser> students;

    public Session(Integer sessionId, String sessionName, LocalDate startDate, LocalDate endDate,
                   Integer thumbnailId, String thumbnailName, long thumbnailSize, int thumbnailWidth, int thumbnailHeight,
                   boolean isPaid, Integer maxStudents, Integer sessionFee,
                   SessionStatus sessionStatus, List<NsUser> students) {
        this(sessionId,
                sessionName,
                new Period(startDate, endDate),
                new Thumbnail(thumbnailId, thumbnailName, new FileSize(thumbnailSize),
                        new FileDimensions(thumbnailWidth, thumbnailHeight)),
                SessionType.determineSessionType(isPaid, maxStudents, sessionFee),
                sessionStatus,
                students);
    }

    public Session(Integer sessionId, String sessionName, Period sessionPeriod, Thumbnail thumbnail,
                   SessionType sessionType, SessionStatus sessionStatus, List<NsUser> students) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionPeriod = sessionPeriod;
        this.thumbnail = thumbnail;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.students = students;
    }

    public boolean isRecruiting() {
        return sessionStatus == SessionStatus.RECRUITING;
    }
}
