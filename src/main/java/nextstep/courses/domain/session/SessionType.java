package nextstep.courses.domain.session;

public class SessionType {
    private boolean isFree;
    private Integer maxAttendance;

    private SessionType(boolean isFree, Integer maxAttendance) {
        this.isFree = isFree;
        this.maxAttendance = maxAttendance;
    }

    private SessionType(boolean isFree) {
        this(isFree, null);
    }

    public static SessionType freeSession() {
        return new SessionType(true);
    }

    public static SessionType notFreeSession(int maxAttendance) {
        if (maxAttendance <= 0) {
            throw new IllegalArgumentException("최대 참가 인원수는 0이하일 수 없습니다.");
        }
        return new SessionType(false, maxAttendance);
    }

    public boolean canRegisterNewUser(int currentRegisteredUserCount) {
        return isFree || currentRegisteredUserCount < maxAttendance;
    }

    public boolean isFree() {
        return isFree;
    }

    public Integer getMaxAttendance() {
        return maxAttendance;
    }
}
