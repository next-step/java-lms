package nextstep.courses.domain;

import java.util.Objects;

public class SessionRegistration {
    private final SessionStatus sessionStatus;

    private final SessionRecruitStatus sessionRecruitStatus;

    private final int maxUserCount;

    public SessionRegistration(SessionStatus sessionStatus, SessionRecruitStatus sessionRecruitStatus, int maxUserCount) {
        this.sessionStatus = sessionStatus;
        this.sessionRecruitStatus = sessionRecruitStatus;
        this.maxUserCount = maxUserCount;
    }

    public static SessionRegistration ready(SessionRecruitStatus sessionRecruitStatus, int maxUserCount) {
        return new SessionRegistration(SessionStatus.READY, sessionRecruitStatus, maxUserCount);
    }

    public void validate(int userCount) {
        if (this.sessionRecruitStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("강의가 모집중이지 않습니다.");
        }

        if (this.sessionStatus.isClose()) {
            throw new IllegalArgumentException("강의가 종료되었습니다.");
        }

        if (maxUserCount <= userCount) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionRecruitStatus getSessionRecruitStatus() {
        return sessionRecruitStatus;
    }

    public int getMaxUserCount() {
        return maxUserCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionRegistration that = (SessionRegistration) o;
        return maxUserCount == that.maxUserCount && sessionStatus == that.sessionStatus && sessionRecruitStatus == that.sessionRecruitStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionStatus, sessionRecruitStatus, maxUserCount);
    }
}
