package nextstep.courses.infrastructure;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionImages;
import nextstep.courses.enumeration.SessionStatus;
import nextstep.courses.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private Session freeSession;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        freeSession = FreeSession.of(1L,
                1L,
                "무료강의",
                new SessionImages(List.of(SessionImage.of(1L, 1L,"url", "GIF", 1000L, 300L, 200L))),
                SessionStatus.REGISTERING,
                LocalDateTime.of(2023, Month.DECEMBER, 3, 15, 0, 0),
                LocalDateTime.of(2023, Month.DECEMBER, 10, 15, 0, 0));
    }

    @Test
    @DisplayName("강의를 생성한다.")
    void createSessionTest() {
        int count = sessionRepository.save(freeSession);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("생성된 무료강의를 읽어온다.")
    void readSessionTest() {
        sessionRepository.save(freeSession);
        Optional<Session> sessionOptional = sessionRepository.findById(1L);
        sessionOptional.ifPresent(session -> assertThat(session.getTitle()).isEqualTo("무료강의"));
    }

    @Test
    @DisplayName("강의 제목을 수정 한다.")
    void updateSessionTest() {
        sessionRepository.save(freeSession);
        int count = sessionRepository.updateTitle("무료강의!!!!!", 1L);
        assertThat(count).isEqualTo(1);

        Optional<Session> sessionOptional = sessionRepository.findById(1L);
        sessionOptional.ifPresent(session -> assertThat(session.getTitle()).isEqualTo("무료강의!!!!!"));
    }

    @Test
    @DisplayName("생성한 무료강의를 삭제한다.")
    void deleteSessionTest() {
        sessionRepository.save(freeSession);
        int count = sessionRepository.delete(1L);
        assertThat(count).isEqualTo(1);

        assertThatThrownBy(() -> sessionRepository.findById(1L))
                .isInstanceOf(EmptyResultDataAccessException.class);

    }
}
