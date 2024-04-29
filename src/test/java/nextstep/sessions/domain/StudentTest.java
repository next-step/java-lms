package nextstep.sessions.domain;

import nextstep.sessions.domain.builder.StudentBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {

    @Test
    void approve() {
        Student selectedStudent = new StudentBuilder().withIsSelected(true).build();

        selectedStudent.approve();

        assertThat(selectedStudent).isEqualTo(new StudentBuilder().withIsSelected(true).withIsApproved(true).build());
    }

    @Test
    void disApprove() {
        Student nonSelectedStudent = new StudentBuilder().withIsSelected(false).build();

        nonSelectedStudent.disApprove();

        assertThat(nonSelectedStudent).isEqualTo(new StudentBuilder().withIsSelected(false).withIsApproved(false).build());
    }

    @Test
    void approveException() {
        Student nonSelectedStudent = new StudentBuilder().withIsSelected(false).build();

        assertThatThrownBy(nonSelectedStudent::approve).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("선발되지 않은 학생은 승인할 수 없습니다.");
    }
}
