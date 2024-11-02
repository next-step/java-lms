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
    void getter() {
        Student student = new Student(FS1, NsUserTest.JAVAJIGI, START);

        assertThat(student.getNsUserId()).isEqualTo(1L);
        assertThat(student.getSessionId()).isEqualTo(1L);
        assertThat(student.getCreatedAt()).isEqualTo(START);
    }
}