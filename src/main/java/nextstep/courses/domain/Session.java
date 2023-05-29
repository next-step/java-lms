package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionState;
import nextstep.courses.exception.SessionExpiredException;
import nextstep.courses.exception.SessionNotOpenException;

public class Session {
    private SessionDate sessionDate;
    private String coverImagePath;
    private boolean isFree;
    private SessionStatus status;
    private SessionState state;

    public Session(String startDate, String endDate, String coverImagePath, boolean isFree, int maxCapacity) {
        this(new SessionDate(startDate, endDate), coverImagePath, isFree, maxCapacity);
    }

    public Session(SessionDate sessionDate, String coverImagePath, boolean isFree, int maxCapacity) {
        this.sessionDate = sessionDate;
        this.isFree = isFree;
        this.status = new SessionStatus(maxCapacity);
        this.state = SessionState.PREPARING;
        this.coverImagePath = coverImagePath;

    }

    public void signUpStudent(Student student) {
        if (!state.equals(SessionState.PROCEEDING)) {
            throw new SessionNotOpenException("강의가 모집중이 아니어서 신청이 불가합니다.");
        }

        status.signUp(student);
    }


    public void setSessionState(SessionState requestState) {
        if (sessionDate.isExpired()) {
            throw new SessionExpiredException("강의종료일이 경과하여 상태 변경이 불가합니다.");
        }

        state = requestState;
    }

    public boolean equalsState(SessionState state) {
        return this.state.equals(state);
    }

    public int getSignedUpStatus() {
        return status.getStudentsSize();
    }
}
