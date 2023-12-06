package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

import static nextstep.courses.domain.SessionState.READY;

public class PaidSession extends Session {
    private int maxUserCount;
    private int fee;

    private List<Payment> paymentHistory;

    private PaidSession(SessionState state, RegisteredUsers registeredUsers, SessionImage coverImage, SessionDuration duration,
                        int maxUserCount, int fee, List<Payment> paymentHistory) {
        super(state, registeredUsers, coverImage, duration);

        validateMaxUserCount(maxUserCount);
        validateFee(fee);

        this.maxUserCount = maxUserCount;
        this.fee = fee;
        this.paymentHistory = paymentHistory;
    }

    public PaidSession(SessionDuration duration, SessionImage coverImage, int maxUserCount, int fee) {
        this(
                READY,
                new RegisteredUsers(),
                coverImage,
                duration,
                maxUserCount,
                fee,
                new ArrayList<>()
        );
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
        throw new UnsupportedOperationException("유료 강의는 지불 정보가 있어야지 등록 가능합니다.");
    }

    public void registerUser(NsUser user, Payment payment) {
        if (payment.isSameUser(user) == false) {
            throw new IllegalArgumentException("지불 정보와 등록하는 유저가 일치하지 않습니다.");
        }

        if (payment.isSameAmountWith(this.fee) == false) {
            throw new IllegalArgumentException("수강료와 지불 금액이 동일하지 않습니다.");
        }

        if (super.isTheNumberOfRegisteredUserLessThan(this.maxUserCount) == false) {
            throw new IllegalStateException("이 강의의 최대 등록 가능 인원에 도달했습니다. 더 이상 사용자를 추가할 수 없습니다.");
        }


        super.registerUser(user);
        this.paymentHistory.add(payment);
    }
}
