package nextstep.lms.infrastructure;

import nextstep.lms.domain.Students;
import nextstep.lms.repository.StudentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@JdbcTest
class JdbcStudentsRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentsRepository studentsRepository;

    @BeforeEach
    void setUp() {
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
    }

    @Test
    void create_read() {
        int count = studentsRepository.save(1L, 1L);
        count += studentsRepository.save(2L, 1L);
        count += studentsRepository.save(3L, 1L);
        assertThat(count).isEqualTo(3);
        Students savedStudents = studentsRepository.findBySession(1L);
        assertThat(savedStudents.getStudents()).contains(1L, 2L, 3L);
    }
}