package nextstep.lms.domain;

import nextstep.lms.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class StudentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("학생 저장 테스트")
    void saveTest() {
        NsUser sanjigi = NsUserTest.SANJIGI;
        Session session = SessionTest.CLASS_ONE;

        Student student = session.enroll(sanjigi);

        int count = studentRepository.save(student);

        assertThat(count)
                .isEqualTo(1);
    }
}