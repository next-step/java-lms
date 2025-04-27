package nextstep.session.infrastructure;

import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5));

        PaymentPolicy policy = new PaidPaymentPolicy(800_000, 10);
        Session session = new Session.Builder()
                .id(1L)
                .title("TestSession")
                .coverImage(null)
                .duration(duration)
                .paymentPolicy(policy)
                .enrolledStudents(null)
                .status(SessionStatus.PREPARING)
                .build();

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());

        LOGGER.debug("Session: title={}", savedSession.getTitle());
    }

    @Test
    void crudWithCoverImage() {
        CoverImage coverImage = new CoverImage.Builder()
                .id(1L)
                .fileName("test cover image")
                .fileSize(100)
                .imageFormat("jpg")
                .imageSize(300, 200)
                .build();
        CoverImageRepository coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        coverImageRepository.save(coverImage);
        CoverImage savedCoverImage = coverImageRepository.findById(1L);

        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5));

        PaymentPolicy policy = new PaidPaymentPolicy(800_000, 10);
        Session session = new Session.Builder()
                .id(1L)
                .title("TestSession")
                .coverImage(savedCoverImage)
                .duration(duration)
                .paymentPolicy(policy)
                .enrolledStudents(null)
                .status(SessionStatus.PREPARING)
                .build();

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());
        assertThat(coverImage.getFileName()).isEqualTo(savedCoverImage.getFileName());

        LOGGER.debug("Session: title={}", savedSession.getTitle());
        LOGGER.debug("CoverImage: fileName={}", savedSession.getCoverImage().getFileName());
    }
}
