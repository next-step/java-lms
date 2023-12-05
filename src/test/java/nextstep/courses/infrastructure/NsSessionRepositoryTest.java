package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.courses.domain.entity.NsSession;
import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.infrastructure.session.JdbcSessionRepository;

@JdbcTest
public class NsSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NsSessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void save_session() {
        LocalDate startDate = LocalDate.of(2023, Month.NOVEMBER, 29);
        LocalDate endDate = LocalDate.of(2023, Month.NOVEMBER, 30);
        NsSession nsSession = NsSession.freeOf(1L,
                                               CoverImage.DEFAULT_IMAGE,
                                               "free",
                                               "open",
                                               startDate,
                                               endDate);

        int savedCount = sessionRepository.save(nsSession);
        assertThat(savedCount).isEqualTo(1);
    }

    @Test
    void find_session() {
        LocalDate startDate = LocalDate.of(2023, Month.NOVEMBER, 29);
        LocalDate endDate = LocalDate.of(2023, Month.NOVEMBER, 30);
        NsSession nsSession = NsSession.freeOf(1L,
                                               CoverImage.DEFAULT_IMAGE,
                                               "free",
                                               "open",
                                               startDate,
                                               endDate);

        sessionRepository.save(nsSession);
        NsSession result = sessionRepository.findById(1L);
        assertThat(nsSession.getCourseId()).isEqualTo(result.getCourseId());
        assertThat(nsSession.getFee()).isEqualTo(result.getFee());
        LOGGER.debug("Session: {}", result);
    }
}
