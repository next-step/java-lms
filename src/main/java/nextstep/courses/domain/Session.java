package nextstep.courses.domain;

import nextstep.courses.CannotEnrollStateException;
import nextstep.courses.ExceedMaxAttendanceCountException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private Long id;

    private CoverImg coverImg;

    private SessionStatus sessionStatus;

    private LocalDate startDate;

    private LocalDate endDate;

    private SessionUsers sessionUsers;

    public Session() {}

    public Session(boolean isFree, SessionStatus sessionStatus, Integer maxAttendance) {
        this.sessionStatus = sessionStatus;
        this.sessionUsers = new SessionUsers(isFree, maxAttendance);
    }

    public void addUser(NsUser nsUser) {
        if (!sessionStatus.equals(SessionStatus.ENROLL)) {
            throw new CannotEnrollStateException("수강 인원 모집중인 강의가 아닙니다.");
        }
        sessionUsers.addUser(nsUser);
    }

    public int attendUserCount() {
        return sessionUsers.totalAttendUsersCount();
    }

}
