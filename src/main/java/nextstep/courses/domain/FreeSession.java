package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class FreeSession extends Session {

    public FreeSession(Long id, LocalDateTime beginDt, LocalDateTime endDt, SessionCover sessionCover, Course course) {
        super(id, beginDt, endDt, sessionCover, course);
    }

    @Override
    public void enroll(NsUser participant, Long amount) {
        validateStatus();
        this.participants.add(participant);
    }
}
