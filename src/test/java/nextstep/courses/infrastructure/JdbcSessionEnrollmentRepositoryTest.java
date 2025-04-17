package nextstep.courses.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcOperations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JdbcSessionEnrollmentRepositoryTest {
    private JdbcSessionEnrollmentRepository repository;
    private JdbcOperations jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcOperations.class);
        repository = new JdbcSessionEnrollmentRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("세션 수강 신청을 저장한다")
    void save() {
        // given
        Long sessionId = 1L;
        Long userId = 1L;

        // when
        repository.save(sessionId, userId);

        // then
        verify(jdbcTemplate).update(
                anyString(),
                eq(sessionId),
                eq(userId),
                eq("Y"),
                any(LocalDateTime.class)
        );
    }

    @Test
    @DisplayName("세션 ID로 등록된 사용자 ID 목록을 조회한다")
    void findUserIdsBySessionId() {
        // given
        Long sessionId = 1L;
        List<Long> expectedUserIds = Arrays.asList(1L, 2L, 3L);
        when(jdbcTemplate.queryForList(anyString(), eq(Long.class), eq(sessionId)))
                .thenReturn(expectedUserIds);

        // when
        List<Long> actualUserIds = repository.findUserIdsBySessionId(sessionId);

        // then
        assertThat(actualUserIds).isEqualTo(expectedUserIds);
    }
} 