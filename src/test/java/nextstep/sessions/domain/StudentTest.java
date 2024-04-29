package nextstep.sessions.domain;

import nextstep.sessions.domain.builder.StudentBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {

    @Test
    void approve() {
        Student selectedStudent = new StudentBuilder().withIsSelected(true).build();

        selectedStudent.approve();

        assertThat(selectedStudent).isEqualTo(new StudentBuilder().withIsSelected(true).withIsApproved(true).build());
    }
}
