package nextstep.sessions.infrastructure;

import static nextstep.sessions.testFixture.SessionBuilder.aSession;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.HashSet;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.students.Student;
import nextstep.sessions.domain.students.StudentRepository;
import nextstep.sessions.domain.students.Students;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class StudentRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private StudentRepository studentRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        this.sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        this.studentRepository = new JdbcStudentRepository(jdbcTemplate);
        this.userRepository = new JdbcUserRepository(jdbcTemplate);
    }


    @Test
    void userUpdateAndFind() {
        Session session = aSession().build();
        int count = this.sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = this.sessionRepository.findAll().stream()
            .findFirst()
            .orElseThrow();
        savedSession.recruitStart();
        NsUser user = this.userRepository.findByUserId("javajigi").orElseThrow();
        Student student = new Student(savedSession, user, LocalDateTime.of(2023, 6, 2, 13, 0), null);
        savedSession.enrollment(new Students(new HashSet<>()), student);
        this.studentRepository.save(student);

        Students students = studentRepository.findAllBySessionId(savedSession.getId());

        assertThat(students.getStudents().size()).isEqualTo(1);
    }
}
