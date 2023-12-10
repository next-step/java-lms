package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.PaySession;
import nextstep.courses.domain.session.repository.CoverImageRepository;
import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.domain.session.repository.StudentRepository;
import nextstep.courses.domain.session.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
public class JdbcSessionRepositoryTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;
    private StudentRepository studentRepository;
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, coverImageRepository, studentRepository);
    }

    @DisplayName("강의 아이디를 전달받아 해당하는 강의를 조회한다.")
    @Test
    void findSession() {
        // given
        Long sessionId = 1L;
        studentRepository.save(new Student(sessionId, 1L));
        studentRepository.save(new Student(sessionId, 2L));
        studentRepository.save(new Student(sessionId, 3L));

        // when
        PaySession paySession = sessionRepository.findPaySessionById(sessionId)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 강의가 없습니다."));

        // then
        assertThat(paySession).isNotNull();
    }
}
