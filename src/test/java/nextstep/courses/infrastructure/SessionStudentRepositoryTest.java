package nextstep.courses.infrastructure;

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
public class SessionStudentRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    JdbcSessionStudentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new JdbcSessionStudentRepository(jdbcTemplate);

        // 테스트용 데이터 삽입 (session, ns_user)
        jdbcTemplate.update("insert into course (id, title, creator_id, created_at) values (1, '강의', 1, current_timestamp)");
        jdbcTemplate.update("insert into session (id, name, type, start_date, end_date, status, course_id) " +
                "values (100, '세션', 'FREE', current_date, current_date + 1, 'RECRUITING', 1)");
        jdbcTemplate.update("insert into ns_user (id, user_id, password, name, email, created_at) " +
                "values (200, 'student1', 'pass', '학생1', 'student@example.com', current_timestamp)");
    }

    @Test
    @DisplayName("학생 등록, 제외, 조회 테스트")
    void registerStudentTest() {
        Long sessionId = 100L;
        Long studentId = 200L;

        repository.register(sessionId, studentId);

        List<Long> studentIds = repository.findStudentIdsBySessionId(sessionId);
        assertThat(studentIds).containsExactly(studentId);
        assertThat(repository.isRegistered(sessionId, studentId)).isTrue();

        repository.unregister(sessionId, studentId);
        assertThat(repository.isRegistered(sessionId, studentId)).isFalse();
    }

    @Test
    @DisplayName("세션 id로 모든 학생 삭제 테스트")
    void deleteAllBySessionId() {
        Long sessionId = 100L;
        Long student1 = 200L;
        Long student2 = 201L;

        jdbcTemplate.update("insert into ns_user (id, user_id, password, name, email, created_at) " +
                        "values (?, ?, ?, ?, ?, current_timestamp)",
                student2, "student2", "pass", "학생2", "s2@example.com");

        repository.register(sessionId, student1);
        repository.register(sessionId, student2);
        assertThat(repository.findStudentIdsBySessionId(sessionId)).hasSize(2);

        repository.deleteAllBySessionId(sessionId);
        assertThat(repository.findStudentIdsBySessionId(sessionId)).isEmpty();
    }

    @Test
    @DisplayName("학생 id로 모든 세션 삭제 테스트")
    void deleteAllByStudentId() {
        Long sessionId = 100L;
        Long studentId = 200L;

        repository.register(sessionId, studentId);
        assertThat(repository.findStudentIdsBySessionId(sessionId)).hasSize(1);

        repository.deleteAllByStudentId(studentId);
        assertThat(repository.findStudentIdsBySessionId(sessionId)).isEmpty();
    }
}
