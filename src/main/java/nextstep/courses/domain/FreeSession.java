package nextstep.courses.domain;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.exception.SessionStateException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class FreeSession extends Session {

    public FreeSession(CoverImage image, LocalDate start, LocalDate end, SessionState state) {
        super(image, start, end, state);
    }

    @Override
    public void apply(NsUser loginUser) {
        checkState();
        participants.add(loginUser);
    }

    private void checkState() {
        if (state.isNotRecruiting()) {
            throw new SessionStateException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다");
        }
    }
}
