package nextstep.courses.infrastructure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcImageRepositoryTest {

    @Autowired
    private JdbcImageRepository jdbcImageRepository;

    @DisplayName("SessionId를 이용하여 Image 조회")
    @Test
    void findBySessionIdx() {
        Assertions.assertThatCode(() -> jdbcImageRepository.findBySession(1L))
                .doesNotThrowAnyException();
    }
}
