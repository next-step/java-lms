package nextstep.lms.domain;

import nextstep.lms.enums.StudentStatusEnum;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentsTest {
    @DisplayName("수강신청 가능")
    @Test
    void 수강생_추가() {
        Student student1 = new Student(NsUserTest.JAVAJIGI.getId(), 1L);
        Student student2 = new Student(NsUserTest.SANJIGI.getId(), 1L);
        Students students = new Students(Arrays.asList(student1));
        int existingStudentsSize = students.size();
        students.enroll(student2);
        assertThat(students.size()).isEqualTo(existingStudentsSize + 1);
    }

    @DisplayName("중복 수강신청시 예외 발생")
    @Test
    void 중복_수강신청() {
        Student student = new Student(NsUserTest.JAVAJIGI.getId(), 1L);
        Students students = new Students(Arrays.asList(student));

        assertThatIllegalArgumentException().isThrownBy(() -> students.enroll(student))
                .withMessage("이미 수강중인 강의입니다.");
    }

    @DisplayName("유료강의 최대 수강 인원을 초과한다면 예외 발생")
    @Test
    void 최대_수강_인원_초과() {
        Student student = new Student(NsUserTest.JAVAJIGI.getId(), 1L, StudentStatusEnum.SELECTED.name());
        Students students = new Students(Arrays.asList(student));
        assertThatIllegalArgumentException().isThrownBy(() -> students.capacityCheck(1))
                .withMessage("수강생이 모두 선발됐습니다.");
    }

    @DisplayName("수강생 선발")
    @Test
    void 수강생_선발() {
        Student student = new Student(NsUserTest.JAVAJIGI.getId(), 1L);
        Students students = new Students(Arrays.asList(student));
        Student expectedStudent = new Student(NsUserTest.JAVAJIGI.getId(), 1L, StudentStatusEnum.SELECTED.name());

        assertThat(students.selection(student)).isEqualTo(expectedStudent);
    }

    @DisplayName("존재하지 않는 수강생 선발 처리 시 예외 발생")
    @Test
    void 없는_수강생_선발() {
        Student otherStudent = new Student(NsUserTest.JAVAJIGI.getId(), 1L);
        Students students = new Students(new ArrayList<>());
        assertThatIllegalArgumentException().isThrownBy(() -> students.selection(otherStudent))
                .withMessage("강의 신청자가 아닙니다.");
    }

    @DisplayName("수강생 미선발")
    @Test
    void 수강생_미선발() {
        Student student = new Student(NsUserTest.JAVAJIGI.getId(), 1L);
        Students students = new Students(Arrays.asList(student));
        Student expectedStudent = new Student(NsUserTest.JAVAJIGI.getId(), 1L, StudentStatusEnum.NON_SELECTED.name());

        assertThat(students.nonSelection(student)).isEqualTo(expectedStudent);
    }

    @DisplayName("존재하지 않는 수강생 미선발 처리 시 예외 발생")
    @Test
    void 없는_수강생_미선발() {
        Student otherStudent = new Student(NsUserTest.JAVAJIGI.getId(), 1L);
        Students students = new Students(new ArrayList<>());
        assertThatIllegalArgumentException().isThrownBy(() -> students.nonSelection(otherStudent))
                .withMessage("강의 신청자가 아닙니다.");
    }
}