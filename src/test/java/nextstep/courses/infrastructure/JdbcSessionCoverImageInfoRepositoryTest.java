package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
class JdbcSessionCoverImageInfoRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private SessionCoverImageInfoRepository sessionCoverImageInfoRepository;

    @BeforeEach
    void setUp() {
        sessionCoverImageInfoRepository = new JdbcSessionCoverImageInfoRepository(jdbcTemplate, dataSource);
    }

    @Test
    public void session_cover_image_info_crud() {
        // given
        SessionCoverImageInfo sessionCoverImageInfo = SessionCoverImageInfo.createNewInstance(preparedPaySession(), preparedCoverImageInfo());
        // when
        Long savedId = sessionCoverImageInfoRepository.saveAndGetId(sessionCoverImageInfo);
        // then
        assertThat(savedId).isEqualTo(1L);
    }


    private static CoverImageInfo preparedCoverImageInfo() {
        return CoverImageInfo.createNewInstance(1000L, "jpg", 300L, 200L);
    }

    private static PaySession preparedPaySession() {
        SessionDate sessionDate = SessionDate.of(LocalDateTime.of(2024, 04, 07, 10, 11), LocalDateTime.now());

        PaySession session = PaySession.createNewInstance(
                new Course(),
                SessionInfos.createWithDefault(sessionDate),
                20,
                20000L
        );
        return session;
    }
}