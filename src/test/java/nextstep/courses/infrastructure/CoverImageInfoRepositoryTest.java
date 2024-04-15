package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImageInfo;
import nextstep.courses.domain.CoverImageInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CoverImageInfoRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageInfoRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    private CoverImageInfoRepository coverImageInfoRepository;

    @BeforeEach
    void setUp() {
        coverImageInfoRepository = new JdbcCoverImageInfoRepository(jdbcTemplate, dataSource);
    }

    @Test
    @DisplayName("이미지 CRUD")
    void crud() {
        CoverImageInfo coverImageInfo = CoverImageInfo.createNewInstance(10L, "gif", 300L, 200L);

        Long savedCoverImageId = coverImageInfoRepository.saveAndGetId(coverImageInfo);

        CoverImageInfo savedCoverImageInfo = coverImageInfoRepository.findById(savedCoverImageId);
        assertThat(coverImageInfo.getSize()).isEqualTo(10L);
        LOGGER.debug("CoverImageInfo: {}", savedCoverImageInfo);
    }
}
