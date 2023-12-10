package nextstep.lms.domain;

import nextstep.lms.enums.StudentStatusEnum;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StudentTest {
    @DisplayName("선발 여부 리턴")
    @Test
    void 선발_여부() {
        Student student1 = new Student(NsUserTest.JAVAJIGI.getId(), 1L);
        assertThat(student1.isSelected()).isFalse();
        Student student2 = new Student(NsUserTest.JAVAJIGI.getId(), 1L, StudentStatusEnum.SELECTED);
        assertThat(student2.isSelected()).isTrue();
    }

    @DisplayName("선발 상태로 변경")
    @Test
    void 선발() {
        Student student = new Student(NsUserTest.JAVAJIGI.getId(), 1L);
        Student expectedStudent = new Student(NsUserTest.JAVAJIGI.getId(), 1L, StudentStatusEnum.SELECTED);
        assertThat(student.selection()).isEqualTo(expectedStudent);
    }

    @DisplayName("미선발 상태로 변경")
    @Test
    void 미선발() {
        Student student = new Student(NsUserTest.JAVAJIGI.getId(), 1L);
        Student expectedStudent = new Student(NsUserTest.JAVAJIGI.getId(), 1L, StudentStatusEnum.NON_SELECTED);
        assertThat(student.nonSelection()).isEqualTo(expectedStudent);
    }
}