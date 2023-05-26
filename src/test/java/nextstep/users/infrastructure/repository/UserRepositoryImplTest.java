package nextstep.users.infrastructure.repository;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.dao.NsUserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImplTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUserId() {
        NsUser nsUser = userRepository.findByUserId("javajigi");
        assertThat(nsUser).isNotNull();
        LOGGER.debug("NsUser: {}", nsUser);
    }
}
