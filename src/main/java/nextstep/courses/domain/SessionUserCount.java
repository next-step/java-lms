package nextstep.courses.domain;

import nextstep.courses.exception.SessionUserCountException;

public class SessionUserCount {
    private int userCount;
    private final int maxUserCount;

    public SessionUserCount(int maxUserCount) {
        this(0, maxUserCount);
    }

    public SessionUserCount(int userCount, int maxUserCount) {
        this.userCount = userCount;
        this.maxUserCount = maxUserCount;
    }

    public int userCount() {
        return userCount;
    }

    public void plusUserCount(){
        userCount++;
    }

    public void validateMaxUserCount() {
        if (userCount >= maxUserCount) {
            throw new SessionUserCountException("제한된 수강 신청 인원을 초과 하였습니다.");
        }
    }

}
