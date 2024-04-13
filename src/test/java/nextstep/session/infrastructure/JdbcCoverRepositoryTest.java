package nextstep.session.infrastructure;

import nextstep.session.domain.Cover;
import nextstep.session.domain.CoverRepository;
import nextstep.session.domain.ImageFilePath;
import nextstep.session.domain.Resolution;
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

    private long sessionId;
    private Cover cover;

    @BeforeEach
    void setUp() {
        coverRepository = new JdbcCoverRepository(jdbcTemplate);

        sessionId = 3L;
        cover = new Cover(
                sessionId,
                new Resolution(300, 200),
                new ImageFilePath("/home", "test", "jpg"),
                1_234_567L,
                NsUserTest.JAVAJIGI.getUserId()
        );
    }

    @DisplayName("커버 이미지를 저장하고, 세션의 ID로 조회할 수 있다.")
    @Test
    void saveAndFindBySessionId() {
        // when
        long savedId = coverRepository.save(cover);
        Cover savedCover = coverRepository.findBySessionId(sessionId);

        // then
        assertThat(savedCover.getId())
                .isEqualTo(savedId);
    }

    @DisplayName("커버 이미지를 저장하고, 커버이미지의 ID로 조회할 수 있다.")
    @Test
    void saveAndFindByCoverId() {
        // when
        long savedId = coverRepository.save(cover);
        Cover savedCover = coverRepository.findById(savedId);

        // then
        assertThat(savedCover.getId())
                .isEqualTo(savedId);
    }

    @DisplayName("커버 이미지의 상태를 삭제로 변경한다.")
    @Test
    void updateDeleteStatus() {
        // when
        long savedId = coverRepository.save(cover);
        int updatedCount = coverRepository.updateDeleteStatus(savedId, true);
        Cover foundCover = coverRepository.findBySessionId(sessionId);

        // then
        assertThat(updatedCount)
                .isEqualTo(1);
        assertThat(foundCover.toVO().isDeleted())
                .isTrue();
    }
}
