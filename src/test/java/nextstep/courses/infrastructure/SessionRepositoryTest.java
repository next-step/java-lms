package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.metadata.Period;
import nextstep.courses.domain.session.metadata.coverImage.CoverImage;
import nextstep.courses.domain.session.metadata.coverImage.CoverImageRepository;
import nextstep.courses.domain.session.metadata.coverImage.Dimensions;
import nextstep.courses.domain.session.metadata.coverImage.ImageType;
import nextstep.courses.domain.session.metadata.coverImage.Size;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    SessionRepository sessionRepository;
    Period period;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
    }

    @Test
    @DisplayName("세션 저장 후 조회")
    void crud() {
        Session session = Session.createPaidSession(1L, period, null, Amount.of(10_000), 3);
        int result = sessionRepository.save(session);
        assertEquals(1, result);
        Session found = sessionRepository.findById(1L);
        assertThat(found.price()).isEqualTo(Amount.of(10_000).getAmount());
    }

    @Test
    @DisplayName("커버이미지 같이 저장하는 경우")
    void crudWithCoverImage() {
        CoverImage coverImage = new CoverImage(2L, Size.ofBytes(1024), ImageType.fromExtension("jpg"), new Dimensions(900,600));
        CoverImageRepository coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        coverImageRepository.save(coverImage);
        Session session = Session.createPaidSession(2L, period, coverImage, Amount.of(10_000), 3);
        int result = sessionRepository.save(session);
        assertEquals(1, result);
        Session found = sessionRepository.findById(2L);
        assertThat(found.getCoverImage().getId()).isEqualTo(2L);
    }
}
