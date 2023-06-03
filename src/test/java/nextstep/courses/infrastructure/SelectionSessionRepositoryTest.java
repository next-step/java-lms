package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SelectionSession;
import nextstep.courses.domain.session.SelectionSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SelectionSessionRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SelectionSessionRepository selectionSessionRepository;

    @BeforeEach
    void setUp() {
        selectionSessionRepository = new JdbcSelectionSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("save")
    void save() {
        SelectionSession selectionSession = new SelectionSession(1L, 1L);
        int count = selectionSessionRepository.save(selectionSession);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("select")
    void select() {
        SelectionSession selectionSession = new SelectionSession(2L, 1L);
        selectionSessionRepository.save(selectionSession);

        SelectionSession savedSelectionSession = selectionSessionRepository.findById(1L).orElseThrow();
        assertThat(savedSelectionSession).isEqualTo(selectionSession);
    }

    @Test
    @DisplayName("select by session_id")
    void select_session_id() {
        SelectionSession selectionSession1 = new SelectionSession(1L, 1L);
        SelectionSession selectionSession2 = new SelectionSession(1L, 2L);
        selectionSessionRepository.save(selectionSession1);
        selectionSessionRepository.save(selectionSession2);


        List<SelectionSession> savedSelectionSessions = selectionSessionRepository.findBySessionId(1L).orElseThrow();
        assertThat(savedSelectionSessions.size()).isEqualTo(2);
    }
}
