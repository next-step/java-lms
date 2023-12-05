package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.enumeration.ExtensionType;
import nextstep.courses.repository.SessionImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
public class SessionImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionImageRepository sessionImageRepository;
    SessionImage sessionImage = new SessionImage(1L, 1L,"url", ExtensionType.GIF, 1024L, LocalDateTime.now(), LocalDateTime.now());

    @BeforeEach
    void setUp() {
        sessionImageRepository = new JdbcSessionImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("이미지를 생성한다.")
    void createSessionImageTest() {
        int count = sessionImageRepository.save(sessionImage);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("생성된 이미지를 읽어온다.")
    void readSessionImageTest() {
        sessionImageRepository.save(sessionImage);
        Optional<SessionImage> sessionImageOptional = sessionImageRepository.findById(1L);
        sessionImageOptional.ifPresent(session -> assertThat(session.getImageUrl()).isEqualTo("url"));
    }

    @Test
    @DisplayName("이미지 url을 수정 한다.")
    void updateSessionImageUrlTest() {
        sessionImageRepository.save(sessionImage);
        int count = sessionImageRepository.updateImageUrl("files.url", 1L);
        assertThat(count).isEqualTo(1);

        Optional<SessionImage> sessionImageOptional = sessionImageRepository.findById(1L);
        sessionImageOptional.ifPresent(session -> assertThat(session.getImageUrl()).isEqualTo("files.url"));
    }

    @Test
    @DisplayName("생성한 무료강의를 삭제한다.")
    void deleteSessionImageTest() {
        sessionImageRepository.save(sessionImage);
        int count = sessionImageRepository.delete(1L);
        assertThat(count).isEqualTo(1);

        assertThatThrownBy(() -> sessionImageRepository.findById(1L))
                .isInstanceOf(EmptyResultDataAccessException.class);

    }
}
