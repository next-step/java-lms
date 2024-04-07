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

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CoverImageInfoRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageInfoRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageInfoRepository coverImageInfoRepository;

    @BeforeEach
    void setUp() {
        coverImageInfoRepository = new JdbcCoverImageInfoRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("이미지 CRUD")
    void crud() {
        CoverImageInfo coverImageInfo = CoverImageInfo.builder()
                .size(10L)
                .width(300L)
                .height(200L)
                .imageType("gif")
                .build();

        int save = coverImageInfoRepository.save(coverImageInfo);
        assertThat(save).isEqualTo(1);

        CoverImageInfo savedCoverImageInfo = coverImageInfoRepository.findById(1L);
        assertThat(coverImageInfo.getSize()).isEqualTo(10L);
        LOGGER.debug("CoverImageInfo: {}", savedCoverImageInfo);
    }
}
