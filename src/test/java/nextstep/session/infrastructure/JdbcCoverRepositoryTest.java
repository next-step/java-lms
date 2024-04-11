package nextstep.session.infrastructure;

import nextstep.session.domain.Cover;
import nextstep.session.domain.CoverRepository;
import nextstep.session.domain.ImageFilePath;
import nextstep.session.domain.Resolution;
import nextstep.session.dto.CoverVO;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("커버 이미지를 저장하고 조회할 수 있다.")
    @Test
    void saveAndFind() {
        Cover cover = new Cover(
                new Resolution(300, 200),
                new ImageFilePath("/home", "test", "jpg"),
                1_234_567L,
                NsUserTest.JAVAJIGI
        );

        Long savedId = coverRepository.save(cover.toVO());
        CoverVO savedCover = coverRepository.findById(savedId);

        assertThat(savedCover.getId())
                .isEqualTo(savedId);
    }

    @DisplayName("커버 이미지의 상태를 삭제로 변경한다.")
    @Test
    void updateDeleteStatus() {
        // given
        Cover cover = new Cover(
                new Resolution(300, 200),
                new ImageFilePath("/home", "test", "jpg"),
                1_234_567L,
                NsUserTest.JAVAJIGI
        );

        // when
        Long savedId = coverRepository.save(cover.toVO());
        int updatedCount = coverRepository.updateDeleteStatus(savedId, true);
        CoverVO foundCover = coverRepository.findById(savedId);

        // then
        assertThat(updatedCount).isEqualTo(1);
        assertThat(foundCover.isDeleted()).isTrue();
    }
}
