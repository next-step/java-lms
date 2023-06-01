package nextstep.courses.domain.enrollment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("학생 객체 테스트")
class StudentTest {

    @DisplayName("학생 객체의 유저아이디와 세션아이디가 동일하면 동일한 학생이다")
    @Test
    void equalsStudent() {
        Student student1 = new Student(1L, 10L);
        Student student2 = new Student(1L, 10L);
        Assertions.assertThat(student1).isEqualTo(student2);
    }
}