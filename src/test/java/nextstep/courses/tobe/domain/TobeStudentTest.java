package nextstep.courses.tobe.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.TobeFreeSessionTest.TFS1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class TobeStudentTest {
    @Test
    void create_REJECT_DENIED_성공() {
        assertThatNoException().isThrownBy(() -> {
            TobeStudent student = new TobeStudent(
                    TFS1,
                    NsUserTest.JAVAJIGI,
                    SelectedStatus.REJECTED,
                    ApprovedStatus.DENIED,
                    START);
        });
    }

    @Test
    void create_REJECT_APPROVED_성공() {
        assertThatNoException().isThrownBy(() -> {
            TobeStudent student = new TobeStudent(
                    TFS1,
                    NsUserTest.JAVAJIGI,
                    SelectedStatus.REJECTED,
                    ApprovedStatus.APPROVED,
                    START);
        });
    }

    @Test
    void create_SELECTED_DENIED_성공() {
        assertThatNoException().isThrownBy(() -> {
            TobeStudent student = new TobeStudent(
                    TFS1,
                    NsUserTest.JAVAJIGI,
                    SelectedStatus.SELECTED,
                    ApprovedStatus.DENIED,
                    START);
        });
    }

    @Test
    void create_SELECTED_APPROVED_성공() {
        assertThatNoException().isThrownBy(() -> {
            TobeStudent student = new TobeStudent(
                    TFS1,
                    NsUserTest.JAVAJIGI,
                    SelectedStatus.SELECTED,
                    ApprovedStatus.DENIED,
                    START);
        });
    }

    @Test
    void getter() {
        TobeStudent student = new TobeStudent(TFS1, NsUserTest.JAVAJIGI, SelectedStatus.REJECTED, ApprovedStatus.DENIED, START);

        assertThat(student.getId()).isEqualTo(0L);
        assertThat(student.getNsUserId()).isEqualTo(NsUserTest.JAVAJIGI.getId());
        assertThat(student.getSessionId()).isEqualTo(TFS1.getId());
        assertThat(student.getCreatedAt()).isEqualTo(START);
    }
}
