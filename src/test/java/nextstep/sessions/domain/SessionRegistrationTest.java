package nextstep.sessions.domain;

import static nextstep.sessions.domain.students.StudentStatus.ACCEPTED;
import static nextstep.sessions.testFixture.StudentBuilder.aStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import nextstep.sessions.domain.students.Student;
import nextstep.sessions.domain.students.Students;
import org.junit.jupiter.api.Test;

class SessionRegistrationTest {

    @Test
    void accept() {
        Student student = aStudent().build();
        SessionRegistration sessionRegistration = new SessionRegistration(1);

        sessionRegistration.accept(student);

        assertThat(student.getStudentStatus()).isEqualTo(ACCEPTED);
    }

    @Test
    void accept_fail_overCapacity() {
        Student student1 = aStudent().withStudentStatus(ACCEPTED).build();
        Student student2 = aStudent().build();
        Students students = new Students(new HashSet<>(Set.of(student1, student2)));;
        SessionRegistration sessionRegistration = new SessionRegistration(1, students);

        assertThatThrownBy(() -> sessionRegistration.accept(student2))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("수강인원이 초과되었습니다.");
    }
}