package nextstep.session.domain;

import nextstep.session.StudentNumberExceededException;
import nextstep.students.domain.Students;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentTest {

    @Test
    void 수강_정원_초과_시_예외_발생() {

        Student student = new Student(0L);
        Students students = new Students(1L, 1L);

        assertThatThrownBy(() -> student.signUp(students))
                .isInstanceOf(StudentNumberExceededException.class);
    }
}
