package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.enums.SelectionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.enums.SelectionStatus.*;
import static org.assertj.core.api.Assertions.*;

class SessionStudentTest {

    @DisplayName("선별 상태를 인자로 받아 자신의 상태를 변경한다.")
    @Test
    void changeStatus() {
        // given
        SessionStudent student = createStudent(WAITING);

        // when
        SessionStudent changed = student.changeStatus(APPROVAL);

        // then
        assertThat(changed.getSelectionStatus()).isEqualTo(APPROVAL);
    }

    @DisplayName("인자로 받은 선별 상태가 'WAITING(대기)' 상태이면 예외를 발생시킨다.")
    @Test
    void validateStatus() {
        // given
        SessionStudent student = createStudent(WAITING);

        // when & then
        assertThatThrownBy(() -> student.changeStatus(WAITING)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강생 선별은 승인 또는 거절 중 하나만 선택할 수 있습니다.");
    }

    private SessionStudent createStudent(SelectionStatus selectionStatus) {
        return new SessionStudent(1L, 1L, selectionStatus);
    }
}