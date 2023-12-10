package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionApproval;
import nextstep.courses.domain.session.Student;
import nextstep.courses.domain.session.Students;
import nextstep.courses.domain.session.StudentsRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentsRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentsRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentsRepository studentsRepository;

    @BeforeEach
    void setUp() {
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        int saveCount = studentsRepository.saveAll(1L, Students.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        assertThat(saveCount).isEqualTo(2);
        int updateCount = studentsRepository.updateState(1L, Student.of(NsUserTest.JAVAJIGI, SessionApproval.APPROVAL));
        assertThat(updateCount).isEqualTo(1);
        Students saveStudents = studentsRepository.findBySessionId(1L);
        assertThat(saveStudents.size()).isEqualTo(2);

        LOGGER.debug("saveStudents: {}", saveStudents);
    }
}
