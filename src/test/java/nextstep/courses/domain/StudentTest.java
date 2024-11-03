package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.ApprovedStatus.APPROVED;
import static nextstep.courses.domain.ApprovedStatus.DENIED;
import static nextstep.courses.domain.FreeSessionTest.FS1;
import static nextstep.courses.domain.SelectedStatus.REJECTED;
import static nextstep.courses.domain.SelectedStatus.SELECTED;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class StudentTest {
@Test
void create_REJECT_DENIED_성공() {
    assertThatNoException().isThrownBy(() -> {
        Student student = new Student(
                FS1,
                JAVAJIGI,
                REJECTED,
                DENIED,
                START);
    });
}

    @Test
    void create_REJECT_APPROVED_성공() {
        assertThatNoException().isThrownBy(() -> {
            Student student = new Student(
                    FS1,
                    JAVAJIGI,
                    REJECTED,
                    APPROVED,
                    START);
        });
    }

    @Test
    void create_SELECTED_DENIED_성공() {
        assertThatNoException().isThrownBy(() -> {
            Student student = new Student(
                    FS1,
                    JAVAJIGI,
                    SELECTED,
                    DENIED,
                    START);
        });
    }

    @Test
    void create_SELECTED_APPROVED_성공() {
        assertThatNoException().isThrownBy(() -> {
            Student student = new Student(
                    FS1,
                    JAVAJIGI,
                    SELECTED,
                    DENIED,
                    START);
        });
    }

    @Test
    void getter() {
        Student student = new Student(FS1, JAVAJIGI, REJECTED, DENIED, START);

        assertThat(student.getId()).isEqualTo(0L);
        assertThat(student.getNsUserId()).isEqualTo(JAVAJIGI.getId());
        assertThat(student.getSessionId()).isEqualTo(FS1.getId());
        assertThat(student.getSelectedStatus()).isEqualTo(REJECTED);
        assertThat(student.getApprovedStatus()).isEqualTo(DENIED);
        assertThat(student.getCreatedAt()).isEqualTo(START);
    }

    @Test
    void approved() {
        Student actual = new Student(FS1, JAVAJIGI, SELECTED, DENIED, START);
        Student expected = new Student(FS1, JAVAJIGI, SELECTED, APPROVED, START);

        actual.approved();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void approved_승인처리패스() {
        Student actual = new Student(FS1, JAVAJIGI, REJECTED, DENIED, START);
        Student expected = new Student(FS1, JAVAJIGI, REJECTED, DENIED, START);

        actual.approved();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void denied() {
        Student actual = new Student(FS1, JAVAJIGI, REJECTED, APPROVED, START);
        Student expected = new Student(FS1, JAVAJIGI, REJECTED, DENIED, START);

        actual.denied();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void approved_취소처리패스() {
        Student actual = new Student(FS1, JAVAJIGI, SELECTED, APPROVED, START);
        Student expected = new Student(FS1, JAVAJIGI, SELECTED, APPROVED, START);

        actual.denied();

        assertThat(actual).isEqualTo(expected);
    }
}