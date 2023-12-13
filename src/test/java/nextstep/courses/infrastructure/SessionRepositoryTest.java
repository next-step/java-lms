package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImages;
import nextstep.courses.domain.Session;
import nextstep.courses.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcOperations jdbcTemplates;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplates);
    }

    @Test
    void free_crud() {
        Session session = Session.ofFree(1L, 2L, coverImages(), LocalDate.now(), LocalDate.of(2023, 12, 31));
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session freeSession = sessionRepository.findById(2L);
        assertThat(freeSession.id()).isEqualTo(2L);
        assertThat(freeSession.type()).isEqualTo("FREE");
    }

    @Test
    void paid_crud() {
        Session session = Session.ofPaid(1L, 2L, coverImages(), LocalDate.now(), LocalDate.of(2023, 12, 31), 10, 10_000L);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session paidSession = sessionRepository.findById(3L);
        assertAll(
                () -> assertThat(paidSession.id()).isEqualTo(3L),
                () -> assertThat(paidSession.type()).isEqualTo("PAID"),
                () -> assertThat(paidSession.fee()).isEqualTo(20_000L)
        );
        LOGGER.debug("Session: {}", paidSession);
    }

    private CoverImages coverImages() {
        return new CoverImages(List.of(new CoverImage(1, "jpg", 300, 200)));
    }
}
