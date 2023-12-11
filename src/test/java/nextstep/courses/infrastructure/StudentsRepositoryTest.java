package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.students.Students;
import nextstep.courses.domain.students.StudentsRepository;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

@JdbcTest
class StudentsRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private StudentsRepository studentsRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
    }

    @DisplayName("저장 및 조회 쿼리 테스트")
    @Test
    void crud() {
        // given
        Session session = new Session(1L, SessionStatus.PREPARE, LocalDateTime.now(), LocalDateTime.now().plusDays(5), false, 10);
        Long savedSessionId = sessionRepository.save(session);
        Session savedSession = sessionRepository.findById(savedSessionId);
        studentsRepository.save(savedSession, NsUserTest.JAVAJIGI);
        // when
        Students students = studentsRepository.findBySession(savedSession);
        // then
        Assertions.assertThat(students.size()).isEqualTo(1);
    }

}