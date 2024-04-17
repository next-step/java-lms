package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.List;

public class PaidSession extends Session {
    private Long maxUserCount;
    private Long fee;

    public PaidSession(Long id, String title, LocalDate startDate, LocalDate endDate, CoverImage coverImage, SessionStatus status, List<NsUser> users, Long maxUserCount, Long fee) {
        super(id, title, startDate, endDate, coverImage, status, users);
        this.maxUserCount = maxUserCount;
        this.fee = fee;
    }

    public PaidSession(Long id, String title, LocalDate startDate, LocalDate endDate, CoverImage coverImage, SessionStatus status, Long maxUserCount, Long fee) {
        super(id, title, startDate, endDate, coverImage, status);
        this.maxUserCount = maxUserCount;
        this.fee = fee;
    }

    @Override
    public void join(NsUser user) {
        if (userCount() >= maxUserCount) {
            throw new IllegalArgumentException("수강인원을 초과 하였습니다.");
        }

        if (this.status != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("현재 강의 모집이 오픈하지 않았습니다");
        }

        if (hasUser(user)) {
            throw new IllegalArgumentException("이미 수강중 입니다");
        }

        if (user.isPaymentFor(this)) {
            throw new IllegalArgumentException("해당 강의를 결제한 내역이 없습니다");
        }

        this.userList.add(user);
    }

    public boolean isFee(Long amount) {
        return this.fee == amount;
    }
}
