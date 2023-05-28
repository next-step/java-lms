package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionState;

import java.net.MalformedURLException;
import java.net.URL;

public class Session {
    private SessionDate sessionDate;
    private URL coverImageURL;
    private boolean isFree;
    private SessionStatus status;
    private SessionState state;

    public Session(String startDate, String endDate, String coverImageURL, boolean isFree, int maxCapacity) {
        this(new SessionDate(startDate, endDate), coverImageURL, isFree, maxCapacity);
    }

    public Session(SessionDate sessionDate, String coverImageURL, boolean isFree, int maxCapacity) {
        this.sessionDate = sessionDate;
        this.isFree = isFree;
        this.status = new SessionStatus(maxCapacity);
        this.state = SessionState.PREPARING;

        try {
            this.coverImageURL = new URL(coverImageURL);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("입력된 이미지의 URL 형식이 올바르지 않습니다.");
        }
    }

    public void signUpStudent(String studentId) {
        if (!state.equals(SessionState.PROCEEDING)) {
            throw new RuntimeException("강의가 모집중이 아니어서 신청이 불가합니다.");
        }

        status.signUp(studentId);
    }


    public void setSessionState(SessionState requestState) {
        if (sessionDate.isExpired()) {
            throw new RuntimeException("강의종료일이 경과하여 상태 변경이 불가합니다.");
        }

        state = requestState;
    }

    public boolean equalsState(SessionState state) {
        return this.state.equals(state);
    }

    public int getSignedUpStatus() {
        return status.getSignedUpStatus();
    }
}
