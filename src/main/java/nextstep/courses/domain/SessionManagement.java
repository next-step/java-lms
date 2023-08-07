package nextstep.courses.domain;

import nextstep.courses.NotChangeStatusException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

class SessionManagement {

    private final boolean free;
    private final Students students = new Students();
    private final SessionTime sessionTime;
    private final int maxNumberOfStudent;
    private SessionStatus sessionStatus;

    SessionManagement(boolean free, int maxNumberOfStudent) {
        this.sessionTime = new SessionTime();
        this.sessionStatus = SessionStatus.PREPARING;
        this.free = free;
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public void enrolment(NsUser user) throws IllegalArgumentException {
        checkedProceeding();

        checkedMaxNumberOfStudent();

        students.addStudent(user);
    }

    private void checkedMaxNumberOfStudent() throws IllegalArgumentException {
        if (!students.isPossibleAdd(maxNumberOfStudent)) {
            throw new IllegalArgumentException("수강 인원이 가득 찼습니다.");
        }
    }

    private void checkedProceeding() throws IllegalArgumentException {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("모집 중이 아닙니다.");
        }
    }

    SessionStatus startRecruiting() {
        return changeSessionStatus(SessionStatus.PREPARING, SessionStatus.RECRUITING);
    }

    SessionStatus startSession() {
        SessionStatus sessionStatus = changeSessionStatus(SessionStatus.RECRUITING, SessionStatus.PROCEEDING);
        sessionTime.start(LocalDateTime.now());
        return sessionStatus;
    }

    SessionStatus endSession() {
        SessionStatus sessionStatus = changeSessionStatus(SessionStatus.PROCEEDING, SessionStatus.END);
        sessionTime.end(LocalDateTime.now());
        return sessionStatus;
    }

    private SessionStatus changeSessionStatus(SessionStatus compareStatus, SessionStatus changeStatus) {
        if (this.sessionStatus != compareStatus) {
            throw new NotChangeStatusException();
        }

        return this.sessionStatus = changeStatus;
    }
}