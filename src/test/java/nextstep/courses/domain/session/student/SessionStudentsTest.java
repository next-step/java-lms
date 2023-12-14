package nextstep.courses.domain.session.student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.student.SelectionStatus.*;
import static org.assertj.core.api.Assertions.*;

class SessionStudentsTest {

    @DisplayName("강의-학생을 추가한다.")
    @Test
    void add() {
        // given
        SessionStudents sessionStudents = new SessionStudents();

        // when & then
        assertThat(sessionStudents.add(createStudent(1L))).isTrue();
    }

    @DisplayName("중복 수강신청이면 예외를 발생시킨다.")
    @Test
    void validateDuplicate() {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        SessionStudent student = createStudent(1L);
        sessionStudents.add(student);

        // when & then
        assertThatThrownBy(() -> sessionStudents.add(student)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미 해당 강의를 수강 중 입니다.");
    }

    @DisplayName("선별 상태와 수강생을 인자로 받아 해당 수강생의 선별 상태를 변경한다.")
    @Test
    void selectStudent() {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        SessionStudent student1 = createStudent(1L);
        SessionStudent student2 = createStudent(2L);
        SessionStudent student3 = createStudent(3L);

        sessionStudents.add(student1);
        sessionStudents.add(student2);
        sessionStudents.add(student3);

        // when
        SessionStudent changed1 = sessionStudents.selectStudent(student1, APPROVAL);
        SessionStudent changed2 = sessionStudents.selectStudent(student2, DENIAL);

        // then
        assertThat(changed1.getSelectionStatus()).isEqualTo(APPROVAL);
        assertThat(changed2.getSelectionStatus()).isEqualTo(DENIAL);
    }

    @DisplayName("수강생 선별 시 자신이 포함하고 있는 수강생이 아니면 예외를 던진다.")
    @Test
    void validatePresent() {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        sessionStudents.add(createStudent(1L));
        sessionStudents.add(createStudent(2L));
        sessionStudents.add(createStudent(3L));

        // when & then
        assertThatThrownBy(() -> sessionStudents.selectStudent(createStudent(4L), APPROVAL)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강생이 존재하지 않습니다.");
    }

    private SessionStudent createStudent(Long nsUserId) {
        return new SessionStudent(0L, nsUserId, WAITING);
    }


}