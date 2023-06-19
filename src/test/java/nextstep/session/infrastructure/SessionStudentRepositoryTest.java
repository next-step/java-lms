package nextstep.session.infrastructure;

import nextstep.session.domain.SessionTest;
import nextstep.session.domain.student.SessionStudent;
import nextstep.session.domain.student.SessionStudentRepository;
import nextstep.session.domain.student.SessionStudentStatus;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
class SessionStudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionStudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcSessionStudentRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Long javajigiStudent = studentRepository.enrollStudent(SessionTest.s1.getId(), NsUserTest.JAVAJIGI.getId());
        assertThat(javajigiStudent).isEqualTo(1);

        List<SessionStudent> students = studentRepository.getStudents(SessionTest.s1.getId());
        SessionStudent sessionStudent = studentRepository.getStudentById(javajigiStudent).orElseThrow(() -> new IllegalArgumentException());
        assertTrue(students.contains(sessionStudent));

        studentRepository.changeStudentStatus(SessionTest.s1.getId(), SessionStudentStatus.APPROVE);
        SessionStudent approvedStudent = studentRepository.getStudentById(javajigiStudent).orElseThrow(() -> new IllegalArgumentException());
        assertThat(approvedStudent.getStatus()).isEqualTo(SessionStudentStatus.APPROVE);

        studentRepository.enrollStudent(SessionTest.s1.getId(), NsUserTest.SANJIGI.getId());
        List<NsUser> users = studentRepository.findAllUserBySessionId(SessionTest.s1.getId());
        assertThat(users.size()).isEqualTo(2);
    }
}
