package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.FreeSessionTest.FS1;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class StudentTest {
    @Test
    void create() {
        assertThatNoException().isThrownBy(() -> {
            Student student = new Student(FS1, NsUserTest.JAVAJIGI, START);
        });
    }

    @Test
    void toParameters() {
        Student student = new Student(FS1, NsUserTest.JAVAJIGI, START);
        Object[] actual = student.toParameters();
        Object[] expected = {1L, 1L, START};

        assertThat(actual).isEqualTo(expected);
    }
}