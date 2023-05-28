package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Session {


    private final SessionInfo sessionInfo;
    private final SessionStatus sessionStatus;
    private final SessionTimeLine sessionTimeLine;
    private final Set<Student> students = new HashSet<>();
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
