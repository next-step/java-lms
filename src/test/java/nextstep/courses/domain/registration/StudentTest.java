package nextstep.courses.domain.registration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentTest {

    @DisplayName("Student 객체 생성")
    @Test
    void 객체_생성() {
        Student student = new Student(1L, 1L);
        Assertions.assertThat(student).isEqualTo(new Student(1L, 1L));
    }

}