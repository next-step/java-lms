package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import static nextstep.courses.domain.SessionState.READY;

public class PaidSession extends Session {
    private int maxUserCount;
    private int fee;

    private PaidSession(SessionState state, RegisteredUsers registeredUsers, SessionImage coverImage, SessionDuration duration, int maxUserCount, int fee) {
        super(state, registeredUsers, coverImage, duration);

        validateMaxUserCount(maxUserCount);
        validateFee(fee);

        this.maxUserCount = maxUserCount;
        this.fee = fee;
    }

    public static PaidSession createNewSession(SessionImage coverImage, SessionDuration duration, int maxUserCount, int fee) {
        return new PaidSession(READY, new RegisteredUsers(), coverImage, duration, maxUserCount, fee);
    }

    private static void validateMaxUserCount(int maxUserCount) {
        if (maxUserCount < 0) {
            throw new IllegalArgumentException("최대 수강 인원은 음수일 수 없습니다.");
        }
    }

    private static void validateFee(int fee) {
        if (fee < 0) {
            throw new IllegalArgumentException("강의료가 음수일 수 없습니다.");
        }

        if (fee == 0) {
            throw new IllegalArgumentException("강의료를 0으로 설정하면 곧 무료 강의가 됩니다. 무료 강의는 Session 객체로 생성하십시오.");
        }
    }

    @Override
    public void registerUser(NsUser user) {
        if (super.isTheNumberOfRegisteredUserLessThan(this.maxUserCount) == false) {
            throw new IllegalStateException("이 강의의 최대 등록 가능 인원에 도달했습니다. 더 이상 사용자를 추가할 수 없습니다.");
        }

        super.registerUser(user);
    }
}
