package nextstep.sessions.domain;

import static nextstep.sessions.domain.students.StudentStatus.ACCEPTED;
import static nextstep.sessions.testFixture.SessionBuilder.aSession;
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
        Session session = aSession()
            .withStudents(new Students(new HashSet<>(Set.of(student))))
            .build();

        session.accept(student);

        assertThat(student.getStudentStatus()).isEqualTo(ACCEPTED);
    }

    @Test
    void accept_fail_overCapacity() {
        Student student1 = aStudent().withStudentStatus(ACCEPTED).build();
        Student student2 = aStudent().build();
        Session session = aSession()
            .withStudents(new Students(new HashSet<>(Set.of(student1, student2))))
            .withCapacity(1)
            .build();

        assertThatThrownBy(() -> session.accept(student2))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("수강 인원이 초과되었습니다.");
    }
}