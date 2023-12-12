package nextstep.courses.domain.session.student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.student.SelectionStatus.*;
import static nextstep.users.domain.fixture.DomainFixture.*;
import static org.assertj.core.api.Assertions.*;

class StudentsTest {

    @DisplayName("강의-학생을 추가한다.")
    @Test
    void add() {
        // given
        Students students = new Students();

        // when & then
        assertThat(students.add(createStudent(1L))).isTrue();
    }

    @DisplayName("중복 수강신청이면 예외를 발생시킨다.")
    @Test
    void validateDuplicate() {
        // given
        Students students = new Students();
        Student student = createStudent(1L);
        students.add(student);

        // when & then
        assertThatThrownBy(() -> students.add(student)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미 해당 강의를 수강 중 입니다.");
    }

    @DisplayName("선별 상태와 수강생을 인자로 받아 해당 수강생의 선별 상태를 변경한다.")
    @Test
    void selectStudent() {
        // given
        Students students = new Students();
        Student student1 = createStudent(1L);
        Student student2 = createStudent(2L);
        Student student3 = createStudent(3L);

        students.add(student1);
        students.add(student2);
        students.add(student3);

        // when
        Student changed1 = students.selectStudents(student1, SELECTION);
        Student changed2 = students.selectStudents(student2, DENIAL);

        // then
        assertThat(changed1.getSelectionStatus()).isEqualTo(SELECTION);
        assertThat(changed2.getSelectionStatus()).isEqualTo(DENIAL);
    }

    @DisplayName("수강생 선별 시 자신이 포함하고 있는 수강생이 아니면 예외를 던진다.")
    @Test
    void validatePresent() {
        // given
        Students students = new Students();
        students.add(createStudent(1L));
        students.add(createStudent(2L));
        students.add(createStudent(3L));

        // when & then
        assertThatThrownBy(() -> students.selectStudents(createStudent(4L), SELECTION)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강생이 존재하지 않습니다.");
    }

    private Student createStudent(Long nsUserId) {
        return new Student(0L, nsUserId, WAITING);
    }


}