package nextstep.sessions.domain;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;

public class PaySession extends Session{
    public static final int ZERO = 0;
    private final int maxUserCount;
    private final int price;

    public PaySession(Long id, Long courseId, SessionPeriod sessionPeriod, SessionImage sessionImage, SessionStatus sessionStatus, int maxUserCount, int price) {
        super(id, courseId, sessionPeriod, sessionImage, sessionStatus);
        validatePositive(maxUserCount);
        validatePositive(price);
        this.maxUserCount = maxUserCount;
        this.price = price;
    }

    @Override
    public void signUp(NsUser nsUser) {
        checkSessionStatus();
        checkSessionUserCount();
        this.nsUsers.add(nsUser);
    }

    private void checkSessionUserCount() {
        if (this.nsUsers.size() >= maxUserCount) {
            changeSessionStatusIsClose();
            throw new IllegalArgumentException("유료 강의의 최대 수강인원을 초과할 수 없습니다.");
        }
    }

    private void validatePositive(int number) {
        if (number < ZERO) {
            throw new IllegalArgumentException("0보다 작은 수가 올 수 없습니다.");
        }
    }


}
