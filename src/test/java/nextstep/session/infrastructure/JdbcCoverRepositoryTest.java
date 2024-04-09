package nextstep.session.infrastructure;

import nextstep.session.domain.Cover;
import nextstep.session.domain.ImageFilePath;
import nextstep.session.domain.Resolution;
import nextstep.session.dto.CoverDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcCoverRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverRepository coverRepository;

    @BeforeEach
    void setUp() {
        coverRepository = new JdbcCoverRepository(jdbcTemplate);
    }

    @Test
    void saveAndFind() {
        Cover cover = new Cover(
                new Resolution(300, 200),
                new ImageFilePath("/home", "test", "jpg"),
                1_234_567L
        );

        Long savedId = coverRepository.save(cover.toDto());
        CoverDto savedCover = coverRepository.findById(savedId);

        assertThat(savedCover.getId())
                .isEqualTo(savedId);
    }

    @Test
    void updateDeleteStatus() {
        // given
        Cover cover = new Cover(
                new Resolution(300, 200),
                new ImageFilePath("/home", "test", "jpg"),
                1_234_567L
        );

        // when
        Long savedId = coverRepository.save(cover.toDto());
        int updatedCount = coverRepository.updateDeleteStatus(savedId, true);
        CoverDto foundCover = coverRepository.findById(savedId);

        // then
        assertThat(updatedCount).isEqualTo(1);
        assertThat(foundCover.isDeleted()).isTrue();
    }
}
