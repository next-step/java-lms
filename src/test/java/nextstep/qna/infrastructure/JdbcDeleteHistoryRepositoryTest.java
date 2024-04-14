package nextstep.qna.infrastructure;

import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.qna.domain.ContentType.QUESTION;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.qna.domain.DeleteHistory;
import nextstep.qna.domain.DeleteHistoryRepository;

@JdbcTest
class JdbcDeleteHistoryRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DeleteHistoryRepository deleteHistoryRepository;

    @BeforeEach
    void setUp() {
        deleteHistoryRepository = new JdbcDeleteHistoryRepository(jdbcTemplate);
    }

    @Test
    void saveAll() {
        // Given
        final List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(QUESTION, 1L, JAVAJIGI, LocalDateTime.now()));
        deleteHistories.add(new DeleteHistory(ANSWER, 1L, SANJIGI, LocalDateTime.now()));

        // When
        deleteHistoryRepository.saveAll(deleteHistories);

        // Then
        final int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM delete_history", Integer.class);
        assertThat(count).isEqualTo(2);
    }
}
