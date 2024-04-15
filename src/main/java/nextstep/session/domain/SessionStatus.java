package nextstep.session.domain;

import nextstep.exception.SessionStatusException;
import nextstep.session.type.SessionEnrollType;
import nextstep.session.type.SessionStatusType;

import java.util.List;

public class SessionStatus {

    public static final int READY_NOT_ENROLL_INDEX = 0;
    public static final int READY_ENROLL_INDEX = 1;
    public static final int PROGRESS_NOT_ENROLL_INDEX = 2;
    public static final int PROGRESS_ENROLL_INDEX = 3;
    public static final int FINISHED_INDEX = 4;
    private static final List<SessionStatus> sessionStatusCache;
    private final SessionStatusType sessionStatus;
    private final SessionEnrollType sessionEnrollType;

    static {
        sessionStatusCache = List.of(
                new SessionStatus(SessionStatusType.READY, SessionEnrollType.NOT_ON_ENROLL),
                new SessionStatus(SessionStatusType.READY, SessionEnrollType.ON_ENROLL),
                new SessionStatus(SessionStatusType.PROGRESS, SessionEnrollType.NOT_ON_ENROLL),
                new SessionStatus(SessionStatusType.PROGRESS, SessionEnrollType.ON_ENROLL),
                new SessionStatus(SessionStatusType.FINISHED, SessionEnrollType.NOT_ON_ENROLL)
        );
    }

    private SessionStatus(SessionStatusType sessionStatus, SessionEnrollType sessionEnrollType) {
        this.sessionStatus = sessionStatus;
        this.sessionEnrollType = sessionEnrollType;
    }

    public static SessionStatus create() {
        return sessionStatusCache.get(READY_NOT_ENROLL_INDEX);
    }

    public SessionStatusType getSessionStatus() {
        return this.sessionStatus;
    }

    public SessionEnrollType getSessionEnrollType() {
        return sessionEnrollType;
    }

    public static SessionStatus of(SessionStatusType sessionStatusType) {
        if (sessionStatusType.equals(SessionStatusType.READY)) {
            return sessionStatusCache.get(READY_NOT_ENROLL_INDEX);
        }

        if (sessionStatusType.equals(SessionStatusType.PROGRESS)) {
            return sessionStatusCache.get(PROGRESS_NOT_ENROLL_INDEX);
        }

        if (sessionStatusType.equals(SessionStatusType.FINISHED)) {
            return sessionStatusCache.get(FINISHED_INDEX);
        }

        throw new IllegalArgumentException("해당되는 세션 상태가 존재하지 않습니다.");
    }

    public SessionStatus toNextStatus() {
        if (this.sessionStatus == SessionStatusType.READY && this.sessionEnrollType == SessionEnrollType.NOT_ON_ENROLL) {
            return sessionStatusCache.get(PROGRESS_NOT_ENROLL_INDEX);
        }

        if (this.sessionStatus == SessionStatusType.READY && this.sessionEnrollType == SessionEnrollType.ON_ENROLL) {
            return sessionStatusCache.get(PROGRESS_ENROLL_INDEX);
        }

        if (this.sessionStatus == SessionStatusType.PROGRESS) {
            return sessionStatusCache.get(FINISHED_INDEX);
        }

        throw new SessionStatusException(String.format("다음 상태로 변경할 수 없습니다. 현재 상태 = %s", this.sessionStatus));
    }

    public SessionStatus toPreviousStatus() {
        if (this.sessionStatus == SessionStatusType.PROGRESS && this.sessionEnrollType == SessionEnrollType.NOT_ON_ENROLL) {
            return sessionStatusCache.get(READY_NOT_ENROLL_INDEX);
        }

        if (this.sessionStatus == SessionStatusType.PROGRESS && this.sessionEnrollType == SessionEnrollType.ON_ENROLL) {
            return sessionStatusCache.get(READY_ENROLL_INDEX);
        }

        throw new SessionStatusException(String.format("이전 상태로 변경할 수 없습니다. 현재 상태 = %s", this.sessionStatus));
    }

    public SessionStatus changeEnroll() {
        if (this.sessionStatus == SessionStatusType.READY) {
            return sessionStatusCache.get(READY_ENROLL_INDEX);
        }

        if (this.sessionStatus == SessionStatusType.PROGRESS) {
            return sessionStatusCache.get(PROGRESS_ENROLL_INDEX);
        }

        throw new SessionStatusException("종료 상태에선 모집상태를 변경할 수 없습니다.");
    }

    public SessionStatus changeNotEnroll() {
        if (this.sessionStatus == SessionStatusType.READY) {
            return sessionStatusCache.get(READY_NOT_ENROLL_INDEX);
        }

        if (this.sessionStatus == SessionStatusType.PROGRESS) {
            return sessionStatusCache.get(PROGRESS_NOT_ENROLL_INDEX);
        }

        throw new SessionStatusException("종료 상태에선 모집상태를 변경할 수 없습니다.");
    }

    public boolean canEnroll() {
        return this.sessionEnrollType == SessionEnrollType.ON_ENROLL;
    }


    public boolean onReady() {
        return this.sessionStatus.equals(SessionStatusType.READY);
    }
}
