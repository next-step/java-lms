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
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate) ;
    }

    @Test
    void create_read() {
        int count = studentsRepository.save(1l,1l);
        count += studentsRepository.save(2l,1l);
        count += studentsRepository.save(3l,1l);
        assertThat(count).isEqualTo(3);
        Students savedStudents = studentsRepository.findBySession(1l);
        assertThat(savedStudents.getStudents()).contains(1l, 2l, 3l);
    }
}