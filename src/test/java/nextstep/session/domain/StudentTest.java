package nextstep.session.domain;

import nextstep.session.StudentNumberExceededException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentTest {

    @Test
    void 수강_정원_초과_시_예외_발생() {

        Student student = new Student(0L);

        assertThatThrownBy(() -> student.signUp(NsUser.GUEST_USER))
                .isInstanceOf(StudentNumberExceededException.class);
    }
}
