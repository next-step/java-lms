package nextstep.session.domain;

import nextstep.session.NotProceedingException;
import nextstep.session.NotRecruitingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionStatusTest {

    @ParameterizedTest
    @EnumSource(value = ProgressStatus.class, names = {"END", "READY"})
    void 강의_진행중이_아닐_경우_예외_발생(ProgressStatus progressStatus) {

        SessionStatus status = new SessionStatus(progressStatus, RecruitmentStatus.RECRUITING);

        assertThatThrownBy(status::checkSessionStatus)
                .isInstanceOf(NotProceedingException.class);
    }

    @Test
    void 모집중이_아닐_경우_예외_발생() {
        SessionStatus status = new SessionStatus(ProgressStatus.PROCEEDING, RecruitmentStatus.NOT_RECRUITING);

        assertThatThrownBy(status::checkSessionStatus)
                .isInstanceOf(NotRecruitingException.class);
    }
}
