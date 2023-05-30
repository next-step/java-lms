package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionBuilder;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentRepository;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class StudentRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(StudentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        final UserRepository userRepository = new JdbcUserRepository(jdbcTemplate);
        final SessionRepository sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        studentRepository = new JdbcStudentRepository(jdbcTemplate, userRepository, sessionRepository);
    }

    @Test
    @DisplayName("Student 정보를 테이블에 저장하고 조회합니다.")
    void crud() {
        Student student = new Student(NsUserTest.JAVAJIGI, SessionBuilder.aSession().withId(1L).build());

        log.debug("STUDENT SAVE: {}", student);
        int count = studentRepository.save(student);

        Student savedStudent = studentRepository.findById(1L).get();
        log.debug("STUDENT READ: {}", savedStudent);

        assertAll(
                () -> assertThat(count).isOne(),
                () -> assertThat(savedStudent.getSessionId()).isEqualTo(student.getSessionId()),
                () -> assertThat(savedStudent.getNsUserId()).isEqualTo(student.getNsUserId()),
                () -> assertThat(savedStudent.getCreatedAt()).isEqualTo(student.getCreatedAt())
        );
    }

}
