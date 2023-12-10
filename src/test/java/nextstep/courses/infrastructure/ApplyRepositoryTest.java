package nextstep.courses.infrastructure;

import nextstep.courses.domain.Participants;
import nextstep.courses.repository.ApplyRepository;
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
public class ApplyRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ApplyRepository applyRepository;

    @BeforeEach
    void setUp() {
        applyRepository = new JdbcApplyRepository(jdbcTemplate);

    }

    @Test
    void crud() {
        int count = applyRepository.save(10L, NsUserTest.SANJIGI);
        assertThat(count).isEqualTo(1);

        Participants participants = applyRepository.findBySessionId(10L);
        assertThat(participants.size()).isEqualTo(1);
        LOGGER.debug("participants : {}", participants);
    }

}
