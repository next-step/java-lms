package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcSessionRepository jdbcSessionRepository;

    @Test
    void findByCourseId() {
        Assertions.assertThatCode(() -> jdbcSessionRepository.findByCourseId(1L))
                .doesNotThrowAnyException();

    }
}
