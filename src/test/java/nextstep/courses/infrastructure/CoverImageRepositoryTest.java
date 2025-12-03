package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.CoverImageRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.builder.SessionBuilder;
import nextstep.courses.record.SessionRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CoverImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRespository(jdbcTemplate);
    }

    @Test
    void crud() {
        CoverImage coverImage = new CoverImage(1L, 1L, "png", 300, 200);
        int count = coverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1);

        CoverImage saveCoverImage = coverImageRepository.findById(1L);
        assertThat(saveCoverImage.getSize()).isEqualTo(1L);
    }


}
