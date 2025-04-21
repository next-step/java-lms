package nextstep.courses.infrastructure;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Period;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private JdbcSessionRepository sessionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        jdbcTemplate.update("insert into course (id, title, creator_id, created_at) " +
                "values (1, '샘플 강의', 42, current_timestamp)");
    }

    @Test
    @DisplayName("CRUD 테스트")
    void crudTest() {
        Long courseId = 1L;

        // Create
        FreeSession session = new FreeSession(null, "세션1",
                new Period(LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 10)),
                new Image("test.jpg", "jpeg", 100000, 600, 400),
                SessionStatus.RECRUITING);
        sessionRepository.save(session, courseId);

        // Read (findAll)
        List<Session> sessions = sessionRepository.findAll();
        assertThat(sessions).hasSize(1);

        // Read (findById)
        Session loaded = sessionRepository.findById(sessions.get(0).getId());
        assertThat(loaded.getName()).isEqualTo("세션1");

        // Update
        Session updatedSession = new FreeSession(
                loaded.getId(),
                "수정된 세션",
                loaded.getPeriod(),
                loaded.getCoverImage(),
                loaded.getStatus()
        );
        sessionRepository.update(updatedSession);
        Session updated = sessionRepository.findById(loaded.getId());
        assertThat(updated.getName()).isEqualTo("수정된 세션");

        // Delete
        sessionRepository.deleteById(loaded.getId());
        List<Session> afterDelete = sessionRepository.findAll();
        assertThat(afterDelete).isEmpty();
    }
}
