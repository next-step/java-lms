package nextstep.sessions.domain;

import nextstep.sessions.domain.builder.StudentBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {
    @Test
    void approve() {
        Student selectedStudent = new StudentBuilder().build();

        selectedStudent.approve();

        assertThat(selectedStudent).isEqualTo(new StudentBuilder().withIsApproved(true).build());
    }

    @Test
    void disApprove() {
        Student nonSelectedStudent = new StudentBuilder().build();

        nonSelectedStudent.disApprove();

        assertThat(nonSelectedStudent).isEqualTo(new StudentBuilder().withIsApproved(false).build());
    }
}
