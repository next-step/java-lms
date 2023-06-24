package nextstep.courses.domain;

import nextstep.qna.UnAuthorizedException;

import java.time.LocalDate;

public class SessionEnrollment {

    private final long sessionId;
    private final SessionState sessionState;
    private final RecruitmentState recruitmentState;
    private final SessionStudents sessionStudents;

    public SessionEnrollment(
            long sessionId,
            SessionState sessionState,
            RecruitmentState recruitmentState,
            SessionStudents sessionStudents
    ) {
        validateSessionId(sessionId);
        this.sessionId = sessionId;
        this.sessionState = sessionState;
        this.recruitmentState = recruitmentState;
        this.sessionStudents = sessionStudents;
    }

    public SessionEnrollment changeSessionState(SessionState sessionState, SessionPeriod sessionPeriod) {
        this.sessionState.validate(sessionPeriod, sessionState);
        return this;
    }

    private void validateChangeSessionState() {

    }

    private void validChangeSessionState(SessionPeriod sessionPeriod) {

    }

    public SessionEnrollment changeRecruitmentState(long sessionId, RecruitmentState recruitmentState) {
        // validateSessionId(sessionId);
        return this;
    }

    public SessionEnrollment syncSessionStateAccordingTo(long sessionId, SessionPeriod sessionPeriod) {
        validateSessionId(sessionId);

        SessionState changeSessionState = sessionState.syncSessionStateAccordingTo(LocalDate.now(), sessionPeriod);
        return new SessionEnrollment(sessionId, changeSessionState, recruitmentState, sessionStudents);
    }

    private void validateSessionId(long sessionId) {
        if (sessionId == 0L) {
            throw new IllegalArgumentException("유효하지 않는 세션 아이디에요 :( [입력 값 : " + sessionId + "]");
        }

        if (sessionId != this.sessionId) {
            throw new UnAuthorizedException("세션 아이디가 동일하지 않아요 :( [입력 값 : " + sessionId + "]");
        }
    }


    public int getNumberOfStudents() {
        return sessionStudents.getNumberOfStudents();
    }
}
