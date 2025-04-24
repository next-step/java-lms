package nextstep.courses.infrastructure;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.PaidSession;
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
import java.time.LocalDateTime;
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
        
        // 테이블 초기화
        jdbcTemplate.update("delete from session_student");
        jdbcTemplate.update("delete from session");
        jdbcTemplate.update("delete from image");
        jdbcTemplate.update("delete from ns_user");
        jdbcTemplate.update("delete from course");
        
        // 테스트용 사용자 추가
        jdbcTemplate.update("insert into ns_user (user_id, password, name, created_at) " +
                "values ('test100', 'password', 'Test User', current_timestamp)");
        
        // 테스트용 강의 추가
        jdbcTemplate.update("insert into course (id, title, creator_id, created_at) " +
                "values (1, '샘플 강의', 42, current_timestamp)");
    }

    @Test
    @DisplayName("CRUD 테스트")
    void crudTest() {
        Long courseId = 1L;

        // Create - Free Session
        FreeSession freeSession = new FreeSession(null, "무료 세션",
                new Period(LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 10)),
                new Image("free.jpg", "jpeg", 100000, 600, 400),
                SessionStatus.RECRUITING);
        sessionRepository.save(freeSession, courseId);

        // Create - Paid Session
        PaidSession paidSession = new PaidSession(null, "유료 세션",
                new Period(LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 10)),
                new Image("paid.jpg", "jpeg", 200000, 600, 400),
                SessionStatus.RECRUITING,
                30, 100000);
        sessionRepository.save(paidSession, courseId);

        // Read (findAll)
        List<Session> sessions = sessionRepository.findAll();
        assertThat(sessions).hasSize(2);
        assertThat(sessions.get(0).getName()).isEqualTo("무료 세션");
        assertThat(sessions.get(1).getName()).isEqualTo("유료 세션");

        // Read (findById)
        Session loadedFree = sessionRepository.findById(sessions.get(0).getId());
        assertThat(loadedFree.getName()).isEqualTo("무료 세션");
        assertThat(loadedFree).isInstanceOf(FreeSession.class);

        Session loadedPaid = sessionRepository.findById(sessions.get(1).getId());
        assertThat(loadedPaid.getName()).isEqualTo("유료 세션");
        assertThat(loadedPaid).isInstanceOf(PaidSession.class);
        assertThat(((PaidSession) loadedPaid).getCapacity().getValue()).isEqualTo(30);
        assertThat(((PaidSession) loadedPaid).getTuitionFee().isSameAmount(100000)).isTrue();

        // Update
        Session updatedSession = new FreeSession(
                loadedFree.getId(),
                "수정된 무료 세션",
                loadedFree.getPeriod(),
                loadedFree.getCoverImage(),
                loadedFree.getStatus()
        );
        sessionRepository.update(updatedSession);
        Session updated = sessionRepository.findById(loadedFree.getId());
        assertThat(updated.getName()).isEqualTo("수정된 무료 세션");

        // Delete
        sessionRepository.deleteById(loadedFree.getId());
        sessionRepository.deleteById(loadedPaid.getId());
        List<Session> afterDelete = sessionRepository.findAll();
        assertThat(afterDelete).isEmpty();
    }

    @Test
    @DisplayName("학생 등록 테스트")
    void registerStudentTest() {
        Long courseId = 1L;
        
        // 테스트용 학생 ID 조회
        Long studentId = jdbcTemplate.queryForObject(
            "select id from ns_user where user_id = 'test100'", Long.class);

        // Create session
        FreeSession session = new FreeSession(null, "무료 세션",
                new Period(LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 10)),
                null, // 이미지는 선택사항으로 처리
                SessionStatus.RECRUITING);
        sessionRepository.save(session, courseId);

        // Get session ID
        List<Session> sessions = sessionRepository.findAll();
        Long sessionId = sessions.get(0).getId();

        // Register student
        sessionRepository.registerStudent(sessionId, studentId);

        // Verify registration
        Session loadedSession = sessionRepository.findById(sessionId);
        List<Long> registeredStudents = loadedSession.getStudentIds();
        assertThat(registeredStudents).contains(studentId);
    }
}
