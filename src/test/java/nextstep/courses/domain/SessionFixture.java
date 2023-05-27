package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionFixture {

    public static Session session(int period, int student, SessionStatus sessionStatus, SessionType sessionType, String path, String name) {
        SessionParticipant participant = new SessionParticipant(period, student);
        SessionCoverImage coverImage = new SessionCoverImage(path, name);
        SessionCondition condition = new SessionCondition(sessionStatus, sessionType, coverImage);
        SessionTerm term = new SessionTerm(LocalDateTime.now(), LocalDateTime.now());
        return new Session(participant, condition, term);
    }

}
