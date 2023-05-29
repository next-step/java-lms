package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String coverImage;
    private boolean isFree;
    private SessionStatus sessionStatus;
    private NsUsers nsUsers;

    public Session(LocalDateTime startDate, LocalDateTime endDate, String coverImage, boolean isFree, SessionStatus sessionStatus, NsUsers nsUsers) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImage = coverImage;
        this.isFree = isFree;
        this.sessionStatus = sessionStatus;
        this.nsUsers = nsUsers;
    }

    public Session enrollNsUser(NsUser nsUser) {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("강의가 모집중 일때만 등록 가능합니다.");
        }
        nsUsers.enroll(nsUser);
        return this;
    }


    public int countNsUsers() {
        return nsUsers.count();
    }
}
