package nextstep.courses.tobe.domain;

import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.domain.ApprovedStatus.*;
import static nextstep.courses.domain.SelectedStatus.*;
import static nextstep.courses.tobe.domain.TobeFreeSessionTest.TFS1;
import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class TobeStudentTest {
    @Test
    void create_REJECT_DENIED_성공() {
        assertThatNoException().isThrownBy(() -> {
            TobeStudent student = new TobeStudent(
                    TFS1,
                    JAVAJIGI,
                    REJECTED,
                    DENIED,
                    START);
        });
    }

    @Test
    void create_REJECT_APPROVED_성공() {
        assertThatNoException().isThrownBy(() -> {
            TobeStudent student = new TobeStudent(
                    TFS1,
                    JAVAJIGI,
                    REJECTED,
                    APPROVED,
                    START);
        });
    }

    @Test
    void create_SELECTED_DENIED_성공() {
        assertThatNoException().isThrownBy(() -> {
            TobeStudent student = new TobeStudent(
                    TFS1,
                    JAVAJIGI,
                    SELECTED,
                    DENIED,
                    START);
        });
    }

    @Test
    void create_SELECTED_APPROVED_성공() {
        assertThatNoException().isThrownBy(() -> {
            TobeStudent student = new TobeStudent(
                    TFS1,
                    JAVAJIGI,
                    SELECTED,
                    DENIED,
                    START);
        });
    }

    @Test
    void getter() {
        TobeStudent student = new TobeStudent(TFS1, JAVAJIGI, REJECTED, DENIED, START);

        assertThat(student.getId()).isEqualTo(0L);
        assertThat(student.getNsUserId()).isEqualTo(JAVAJIGI.getId());
        assertThat(student.getSessionId()).isEqualTo(TFS1.getId());
        assertThat(student.getSelectedStatus()).isEqualTo(REJECTED);
        assertThat(student.getApprovedStatus()).isEqualTo(DENIED);
        assertThat(student.getCreatedAt()).isEqualTo(START);
    }

    @Test
    void approved() {
        TobeStudent actual = new TobeStudent(TFS1, JAVAJIGI, SELECTED, DENIED, START);
        TobeStudent expected = new TobeStudent(TFS1, JAVAJIGI, SELECTED, APPROVED, START);

        actual.approved();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void approved_승인처리패스() {
        TobeStudent actual = new TobeStudent(TFS1, JAVAJIGI, REJECTED, DENIED, START);
        TobeStudent expected = new TobeStudent(TFS1, JAVAJIGI, REJECTED, DENIED, START);

        actual.approved();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void denied() {
        TobeStudent actual = new TobeStudent(TFS1, JAVAJIGI, REJECTED, APPROVED, START);
        TobeStudent expected = new TobeStudent(TFS1, JAVAJIGI, REJECTED, DENIED, START);

        actual.denied();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void approved_취소처리패스() {
        TobeStudent actual = new TobeStudent(TFS1, JAVAJIGI, SELECTED, APPROVED, START);
        TobeStudent expected = new TobeStudent(TFS1, JAVAJIGI, SELECTED, APPROVED, START);

        actual.denied();

        assertThat(actual).isEqualTo(expected);
    }
}
