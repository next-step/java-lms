package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcEnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbc;

    private JdbcSessionRepository sessionRepository;
    private JdbcEnrollmentRepository enrollmentRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbc);
        enrollmentRepository = new JdbcEnrollmentRepository(jdbc, sessionRepository);

        // 테스트용 데이터 삽입
        jdbc.update("INSERT INTO member (id, name, email) VALUES (?, ?, ?)",
                1L, "홍길동", "hong@example.com");

        jdbc.update("INSERT INTO session (id, title, start_date, end_date, tuition, current_count, capacity, status, recruitment_status, course_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                1L, "도메인 주도 설계",
                LocalDateTime.now(), LocalDateTime.now().plusDays(30),
                10000L, 0, 20,
                "ONGOING", "RECRUITING", 1L
        );

        // image insert 없이도 session은 생성됨
    }

    @Test
    @DisplayName("Enrollment 저장 후 조회할 수 있다 (Session 포함)")
    void save_and_find_with_session() {
        // when
        enrollmentRepository.save(1L, 1L, EnrollmentStatus.PENDING);

        // then
        List<Enrollment> result = enrollmentRepository.findBySessionId(1L);
        assertThat(result).hasSize(1);

        Enrollment enrollment = result.get(0);
        assertThat(enrollment.getStudent().getId()).isEqualTo(1L);
        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.PENDING);

        // ✅ Session까지 제대로 연결되었는지 확인
        Session session = enrollment.getSession();
        assertThat(session).isNotNull();
        assertThat(session.getTitle()).isEqualTo("도메인 주도 설계");
        assertThat(session.getTuition()).isEqualTo(10000L);
    }

    @Test
    @DisplayName("Enrollment 상태를 변경할 수 있다")
    void update_status() {
        // given
        enrollmentRepository.save(1L, 1L, EnrollmentStatus.PENDING);

        // when
        enrollmentRepository.updateStatus(1L, 1L, EnrollmentStatus.APPROVED);

        // then
        List<Enrollment> result = enrollmentRepository.findBySessionId(1L);
        Enrollment enrollment = result.get(0);

        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
    }
}
