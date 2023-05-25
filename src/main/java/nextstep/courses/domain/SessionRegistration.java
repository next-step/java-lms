package nextstep.courses.domain;

import java.util.Objects;

public class SessionRegistration {
    private final SessionRecruitStatus sessionRecruitStatus;

    private final long maxUserCount;

    public SessionRegistration(SessionRecruitStatus sessionRecruitStatus, long maxUserCount) {
        this.sessionRecruitStatus = sessionRecruitStatus;
        this.maxUserCount = maxUserCount;
    }

    public static SessionRegistration of(SessionRecruitStatus sessionRecruitStatus, long maxUserCount) {
        return new SessionRegistration(sessionRecruitStatus, maxUserCount);
    }

    public void validate(long userCount) {
        if (this.sessionRecruitStatus.isNotRecruiting()) {
            throw new IllegalArgumentException("강의가 모집중이지 않습니다.");
        }

        if (maxUserCount <= userCount) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }
    }

    public void approveValidate(long userCount) {
        if (maxUserCount <= userCount) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }
    }

    public SessionRecruitStatus getSessionRecruitStatus() {
        return sessionRecruitStatus;
    }

    public long getMaxUserCount() {
        return maxUserCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionRegistration that = (SessionRegistration) o;
        return maxUserCount == that.maxUserCount && sessionRecruitStatus == that.sessionRecruitStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionRecruitStatus, maxUserCount);
    }
}
