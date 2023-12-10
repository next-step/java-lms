package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.coverimage.CoverImageRepository;
import nextstep.courses.domain.coverimage.CoverImageType;
import nextstep.courses.domain.coverimage.ImageFileSize;
import nextstep.courses.domain.coverimage.ImageSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class CoverImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        CoverImage coverImage = CoverImage.defaultOf(1L,"test", CoverImageType.GIF,new ImageFileSize(50),new ImageSize(300,200),
            LocalDateTime.now() ,null);
        int count = coverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1);
        CoverImage savedCoverImage = coverImageRepository.findById(1L);
        assertThat(coverImage.getName()).isEqualTo(savedCoverImage.getName());
        LOGGER.debug("Course: {}", savedCoverImage);
    }
}
