package nextstep.courses.domain.sessionuser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionUserTest {

    @DisplayName("이미 취소된 사용자라면 에러를 발생시킵니다.")
    @Test
    void alreadyCancel() {
        // given
        SessionUser student = new SessionUser(1L, 1L, UserType.STUDENT, true);
        SessionUser tutor = new SessionUser(2L, 1L, UserType.TUTOR);
        // then
        // when
        Assertions.assertThatThrownBy(() -> tutor.cancel(student))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 취소된 학생입니다.");
    }

    @DisplayName("사용자 취소 상태를 변경합니다.")
    @Test
    void cancel() {
        // given
        SessionUser student = new SessionUser(1L, 1L, UserType.STUDENT);
        SessionUser tutor = new SessionUser(2L, 1L, UserType.TUTOR);
        // when
        tutor.cancel(student);
        // then
        Assertions.assertThat(student.isCanceled()).isTrue();
    }
}