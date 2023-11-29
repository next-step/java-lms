package nextstep.lms.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session{
    public FreeSession(String name, LocalDateTime startDate, LocalDateTime endDate,
                       CoverImage coverImage, SessionStatus sessionStatus, List<NsUser> students) {
        super(name, startDate, endDate, coverImage, sessionStatus, students);
    }
}
