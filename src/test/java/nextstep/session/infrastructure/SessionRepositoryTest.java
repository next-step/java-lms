package nextstep.session.infrastructure;

import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.image.CoverImage;
import nextstep.session.domain.image.CoverImageRepository;
import nextstep.session.domain.image.CoverImages;
import nextstep.session.domain.session.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        Session session = new SessionBuilder()
                .id(1L)
                .title("TestSession")
                .duration(duration)
                .paymentPolicy(policy)
                .enrolledStudents(null)
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.PREPARING);
        assertThat(session.getRecruitmentStatus()).isEqualTo(RecruitmentStatus.OPEN);

        LOGGER.debug("Session: title={}", savedSession.getTitle());
    }

    @Test
    void crudWithCoverImage() {
        Long sessionId = 1L;

        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5));
        PaymentPolicy policy = new PaidPaymentPolicy(800_000, 10);
        CoverImages savedCoverImages = new CoverImages(getSavedCoverImages(sessionId));
        Session session = new SessionBuilder()
                .id(sessionId)
                .title("TestSession")
                .coverImages(savedCoverImages)
                .duration(duration)
                .paymentPolicy(policy)
                .enrolledStudents(null)
                .sessionStatus(SessionStatus.PREPARING)
                .recruitmentStatus(RecruitmentStatus.OPEN)
                .build();

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(sessionId);
        assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());
        assertThat(session.getSessionStatus()).isEqualTo(SessionStatus.PREPARING);
        assertThat(session.getRecruitmentStatus()).isEqualTo(RecruitmentStatus.OPEN);
        assertThat(savedSession.getCoverImages().size()).isEqualTo(1);

        LOGGER.debug("Session: title={}", savedSession.getTitle());
        LOGGER.debug("CoverImage: fileName={}", savedSession.getCoverImages().get(0).getFileName());
    }
    private List<CoverImage> getSavedCoverImages(Long sessionId) {
        CoverImage coverImage = new CoverImage.Builder()
                .id(1L)
                .fileName("test cover image")
                .fileSize(100)
                .imageFormat("jpg")
                .imageSize(300, 200)
                .sessionId(sessionId)
                .build();
        CoverImageRepository coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        coverImageRepository.save(coverImage);
        CoverImage savedCoverImage = coverImageRepository.findById(1L);
        List<CoverImage> savedCoverImages = new ArrayList<>();
        savedCoverImages.add(savedCoverImage);

        return savedCoverImages;
    }
}
