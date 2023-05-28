package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {


    private final SessionInfo sessionInfo;
    private final SessionStatus sessionStatus;
    private final SessionTimeLine sessionTimeLine;
    // Set<Student>
    private final Long maxNumberOfStudent;

    public Session(Long courseId, Long ownerId, String title, String coverImageInfo,
                   SessionType sessionType, SessionStatus sessionStatus,
                   LocalDateTime createdAt, LocalDateTime closedAt, Long maxNumOfStudent) {

            this(new SessionInfo(courseId, ownerId, title, coverImageInfo, sessionType),
                sessionStatus,
                new SessionTimeLine(createdAt, closedAt),
                maxNumOfStudent
        );
    }

    public Session(SessionInfo sessionInfo, SessionStatus sessionStatus,
                   SessionTimeLine sessionTimeLine, Long maxNumberOfStudent){
        this.sessionInfo = sessionInfo;
        this.sessionStatus = sessionStatus;
        this.sessionTimeLine = sessionTimeLine;
        this.maxNumberOfStudent = maxNumberOfStudent;
    }
}
