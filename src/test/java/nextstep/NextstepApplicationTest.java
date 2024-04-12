package nextstep;

import nextstep.config.BeanConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(BeanConfig.class)
class NextstepApplicationTest {
    @Test
    void contextLoads() {
    }
}
