package nextstep.users.domain;

import nextstep.fixture.TestFixture;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@JdbcTest
public class UserRepositoryTest {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @DisplayName("findByUserId/code")
    @Test
    public void findByUserIdCode() {
        //given
        //when
        Optional<NsUser> user = userRepository.findByUserCode(UserCode.of("javajigi-sql"));
        //then
        assertThat(user.isEmpty()).isFalse();
        LOG.info("NsUser: {}", user.orElseThrow());
    }

    @DisplayName("save")
    @Test
    public void save() {
        //given
        NsUser user = TestFixture.BADAJIGI;
        //when
        NsUser save = userRepository.save(user);
        //then
        assertThat(save).as("").isNotNull();
        assertThat(save.getEmail()).as("").isEqualTo(user.getEmail());
        assertThat(save.getName()).as("").isEqualTo(user.getName());
        fail();
    }

    @DisplayName("findAll")
    @Test
    public void findAll() {
        //given
        NsUser user1 = TestFixture.BADAJIGI;
        NsUser user2 = TestFixture.JAVAJIGI;
        //when
        NsUser save1 = userRepository.save(user1);
        NsUser save2 = userRepository.save(user2);
        List<NsUser> all = userRepository.findAll();
        //then
        assertThat(all).as("").contains(save1);
        assertThat(all).as("").contains(save2);
        assertThat(all.size()).as("").isGreaterThan(2);
        fail();
    }
}
