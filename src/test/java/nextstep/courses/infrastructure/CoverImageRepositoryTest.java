package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.courses.domain.session.metadata.coverImage.CoverImage;
import nextstep.courses.domain.session.metadata.coverImage.CoverImageRepository;
import nextstep.courses.domain.session.metadata.coverImage.Dimensions;
import nextstep.courses.domain.session.metadata.coverImage.ImageType;
import nextstep.courses.domain.session.metadata.coverImage.Size;

@JdbcTest
public class CoverImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageRepositoryTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("DB 이미지 insert 테스트")
    void insert() {
        CoverImage image = new CoverImage(1L, Size.ofBytes(1024), ImageType.fromExtension("jpg"), new Dimensions(900, 600));
        int count = coverImageRepository.save(image);
        assertThat(count).isEqualTo(1);
        CoverImage find = coverImageRepository.findById(1L);
        assertThat(find.getExtension()).isEqualTo(image.getExtension());
        LOGGER.debug("CoverImage : {}", find);
    }
}
