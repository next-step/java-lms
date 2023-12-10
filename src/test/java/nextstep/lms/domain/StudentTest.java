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
        Student student2 = new Student(NsUserTest.JAVAJIGI.getId(), 1L, StudentStatusEnum.SELECTED.name());
        assertThat(student2.isSelected()).isTrue();
    }
}