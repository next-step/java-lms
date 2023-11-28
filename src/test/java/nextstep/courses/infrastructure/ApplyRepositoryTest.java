package nextstep.courses.infrastructure;

import nextstep.courses.domain.Apply;
import nextstep.courses.domain.Session;
import nextstep.courses.repository.ApplyRepository;
import nextstep.courses.repository.FreeSessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class ApplyRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ApplyRepository applyRepository;
    private FreeSessionRepository freeSessionRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        applyRepository = new JdbcApplyRepository(jdbcTemplate);
        freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Apply apply = apply(savedSession(), savedUser());
        int count = applyRepository.save(apply);
        assertThat(count).isEqualTo(1);
        Apply savedApply = applyRepository.findById(1L);
        assertThat(apply).isEqualTo(savedApply);
        LOGGER.debug("Apply: {}", savedApply);
    }

    public Apply apply(Session session, NsUser user) {
        return new Apply(1L, session, user, LocalDateTime.now(), null);
    }

    public Session savedSession() {
        return freeSessionRepository.findById(3L);
    }

    public NsUser savedUser() {
        return userRepository.findById(1L);
    }

}
