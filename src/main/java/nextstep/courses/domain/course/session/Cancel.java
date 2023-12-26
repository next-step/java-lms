package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.session.apply.Applies;
import nextstep.courses.domain.course.session.apply.Apply;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Cancel {
    private Applies applies;

    private SessionState sessionState;

    public Cancel(Applies applies, SessionState sessionState) {
        this.applies = applies;
        this.sessionState = sessionState;
    }

    public Apply cancel(NsUser loginUser, Apply apply, LocalDateTime date) {
        checkUserHasAuthor(loginUser);

        return this.applies.getApplies().stream()
                .filter(savedApply -> cancel(apply, savedApply))
                .findAny()
                .map(savedApply -> savedApply.cancel(date))
                .orElseThrow(() -> new IllegalArgumentException("지원자가 미승인 상태인지 확인하세요."));
    }

    private void checkUserHasAuthor(NsUser loginUser) {
        if(!loginUser.hasAuthor()) {
            throw new IllegalArgumentException("신청을 승인 할 권한이 없습니다.");
        }
    }

    private static boolean cancel(Apply apply, Apply savedApply) {
        return savedApply.isSame(apply) && savedApply.isCanceled();
    }
}
