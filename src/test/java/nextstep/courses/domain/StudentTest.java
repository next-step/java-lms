package nextstep.courses.domain;

import nextstep.courses.domain.code.Selection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    @DisplayName("선발 상태를 승인 상태로 변경 가능하다")
    void approve() {
        Student student = new Student(0L, 0L, Selection.WAITING);
        student.approve();

        Selection actual = student.getSelection();
        Selection expected = Selection.APPROVED;

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
