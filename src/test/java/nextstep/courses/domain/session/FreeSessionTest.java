package nextstep.courses.domain.session;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.domain.SessionImageTest;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FreeSessionTest {
    @Test
    @DisplayName("FreeSession 생성 테스트")
    public void initFreeSession() {
        FreeSession paidSession = new FreeSession(
                1L,
                1L,
                SessionImageTest.SESSION_IMAGE,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(5),
                SessionStatus.READY
        );

        assertThat(paidSession).isInstanceOf(FreeSession.class);
        assertThat(paidSession.getEnrolledStudentCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("FreeSession 등록 테스트")
    public void enrollFreeSession() {
        FreeSession paidSession = new FreeSession(
                1L,
                1L,
                SessionImageTest.SESSION_IMAGE,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(5),
                SessionStatus.RECRUITING
        );

        paidSession.enroll(NsUser.GUEST_USER);

        assertThat(paidSession.getEnrolledStudentCount()).isEqualTo(1);
        assertThat(paidSession.isEnrolled(NsUser.GUEST_USER)).isTrue();
    }

    @Test
    @DisplayName("FreeSession 등록 테스트, 상태 예외 처리 테스트")
    public void enrollFreeSessionStatusErrorTest() {
        FreeSession paidSession = new FreeSession(
                1L,
                1L,
                SessionImageTest.SESSION_IMAGE,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(5),
                SessionStatus.READY
        );


        assertThatThrownBy(() -> paidSession.enroll(NsUser.GUEST_USER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강 모집중인 세션이 아닙니다.");
    }
}