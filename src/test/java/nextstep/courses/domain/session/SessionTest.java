package nextstep.courses.domain.session;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.courses.exception.NotOpenSessionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session(0L, null, null, new SessionCapacity(0, 10),
                "TDD, 클린코드", SessionType.PAY, SessionStatus.READY, RecruitmentStatus.OPEN,
                LocalDateTime.now(), null);
    }

    @Test
    void 강의를_수강신청하다() {
        // given & when
         session.enroll();

        // then
        assertThat(session.getCapacity().getNumber()).isEqualTo(1);
    }

    @Test
    void 강의상태가_모집중이_아니라서_예외가_발생한다() {
        // given
        session.changeStatus(RecruitmentStatus.CLOSE);

        // when & then
        assertThatThrownBy(() -> session.enroll())
                .isInstanceOf(NotOpenSessionException.class)
                .hasMessage("강의상태가 모집중이 아닙니다.");
    }

    @Test
    void 강의의_최대_수용력을_초과하여_예외가_발생한다() {
        // given
        session.changeCapacityMax(0);

        // when & then
        assertThatThrownBy(() -> session.enroll())
                .isInstanceOf(ExceedSessionCapacityException.class)
                .hasMessage("최대 수강인원을 초과했습니다.");
    }

    @Test
    void 강의모집상태를_변경한다() {
        // given
        RecruitmentStatus status = RecruitmentStatus.OPEN;

        // when
        session.changeStatus(status);

        // then
        assertThat(session.getRecruitmentStatus()).isEqualTo(status);
    }

    @Test
    void 강의_최대_수용력을_변경한다() {
        // given

        // when
        session.changeCapacityMax(20);

        // then
        assertThat(session.getCapacity().getNumberMax()).isEqualTo(20);
    }
}
