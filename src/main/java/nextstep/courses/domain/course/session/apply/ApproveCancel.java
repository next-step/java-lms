package nextstep.courses.domain.course.session.apply;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class ApproveCancel {
    private final Applies applies;

    public ApproveCancel(Applies applies) {
        this.applies = applies;
    }

    public Apply approve(NsUser loginUser, Apply apply, LocalDateTime date) {
        checkUserHasAuthor(loginUser);

        return this.applies.approve(apply, date);
    }

    public Apply cancel(NsUser loginUser, Apply apply, LocalDateTime date) {
        checkUserHasAuthor(loginUser);

        return this.applies.cancel(apply, date);
    }

    private void checkUserHasAuthor(NsUser loginUser) {
        if(!loginUser.hasAuthor()) {
            throw new IllegalArgumentException("신청을 승인 할 권한이 없습니다.");
        }
    }
}
