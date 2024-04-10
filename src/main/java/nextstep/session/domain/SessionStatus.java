package nextstep.session.domain;

import nextstep.exception.SessionStatusException;
import nextstep.session.type.SessionStatusType;

import java.util.List;

public class SessionStatus {

    public static final int READY_INDEX = 0;
    public static final int ON_ENROLL_INDEX = 1;
    public static final int FINISHED_INDEX = 2;
    private final static List<SessionStatus> sessionStatusCache;
    private final SessionStatusType sessionStatus;

    static {
        sessionStatusCache = List.of(
                new SessionStatus(SessionStatusType.READY),
                new SessionStatus(SessionStatusType.ON_ENROLL),
                new SessionStatus(SessionStatusType.FINISHED)
        );
    }

    private SessionStatus(SessionStatusType sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public static SessionStatus create() {
        return sessionStatusCache.get(READY_INDEX);
    }

    public SessionStatusType getSessionStatus() {
        return this.sessionStatus;
    }

    public static SessionStatus of(SessionStatusType sessionStatusType) {
        if (sessionStatusType.equals(SessionStatusType.READY)) {
            return sessionStatusCache.get(READY_INDEX);
        }

        if (sessionStatusType.equals(SessionStatusType.ON_ENROLL)) {
            return sessionStatusCache.get(ON_ENROLL_INDEX);
        }

        if (sessionStatusType.equals(SessionStatusType.FINISHED)) {
            return sessionStatusCache.get(FINISHED_INDEX);
        }

        throw new IllegalArgumentException("해당되는 세션 상태가 존재하지 않습니다.");
    }

    public SessionStatus toNextStatus() {
        if (this.sessionStatus == SessionStatusType.READY) {
            return sessionStatusCache.get(ON_ENROLL_INDEX);
        }

        if (this.sessionStatus == SessionStatusType.ON_ENROLL) {
            return sessionStatusCache.get(FINISHED_INDEX);
        }

        throw new SessionStatusException(String.format("다음 상태로 변경할 수 없습니다. 현재 상태 = %s", this.sessionStatus));
    }

    public SessionStatus toPreviousStatus() {
        if (this.sessionStatus == SessionStatusType.ON_ENROLL) {
            return sessionStatusCache.get(READY_INDEX);
        }

        throw new SessionStatusException(String.format("이전 상태로 변경할 수 없습니다. 현재 상태 = %s", this.sessionStatus));
    }

    public boolean canEnroll() {
        return this.sessionStatus == SessionStatusType.ON_ENROLL;
    }


    public boolean onReady() {
        return this.sessionStatus.equals(SessionStatusType.READY);
    }
}
