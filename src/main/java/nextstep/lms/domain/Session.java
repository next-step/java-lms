package nextstep.lms.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    protected String name;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected CoverImage coverImage;
    protected SessionStatus sessionStatus;
    protected List<NsUser> students;

    public Session(String name, LocalDateTime startDate, LocalDateTime endDate,
                   CoverImage coverImage, SessionStatus sessionStatus, List<NsUser> students) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.students = new ArrayList<>(students) ;
    }
}
