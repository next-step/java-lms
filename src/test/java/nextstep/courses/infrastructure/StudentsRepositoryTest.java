package nextstep.courses.infrastructure;

import nextstep.courses.domain.StudentsRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentsRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentsRepository studentsRepository;

    @BeforeEach
    void setUp() {
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        int count = studentsRepository.save(1L, NsUserTest.JAVAJIGI.getId());
        assertThat(count).isEqualTo(1);
        List<NsUser> savedStudents = studentsRepository.findAllBySessionId(1L);
        assertThat(savedStudents.stream().anyMatch(nsUser -> nsUser.matchUser(NsUserTest.JAVAJIGI))).isTrue();
    }
}
