package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.SessionStudent;
import nextstep.courses.infrastructure.engine.SessionStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.SessionStudentFixture.student;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionStudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionStudentRepository sessionStudentRepository;

    @BeforeEach
    void setUp() {
        sessionStudentRepository = new JdbcSessionStudentRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("save()")
    void save() {
        SessionStudent student = student(SESSION_ID, 1L);

        int count = sessionStudentRepository.save(student);

        assertThat(count).isEqualTo(1);
    }

    @ParameterizedTest(name = "[{index}] List<SessionStudent>.get({0}).getNsUserId() == {1}L")
    @CsvSource(value = {"0:1", "1:2"}, delimiter = ':')
    @DisplayName("findAllBySessionId()")
    void findAllBySessionId(int index, Long nsUserId) {
        SessionStudent student1 = student(SESSION_ID, 1L);
        sessionStudentRepository.save(student1);

        SessionStudent student2 = student(SESSION_ID, 2L);
        sessionStudentRepository.save(student2);

        List<SessionStudent> saveEntity = sessionStudentRepository.findAllBySessionId(SESSION_ID);

        assertThat(saveEntity.get(index).getNsUserId()).isEqualTo(nsUserId);
    }

}
