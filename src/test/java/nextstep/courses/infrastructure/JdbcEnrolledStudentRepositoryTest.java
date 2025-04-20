package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
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
class JdbcEnrolledStudentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrolledStudentRepository enrolledStudentRepository;
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        enrolledStudentRepository = new JdbcEnrolledStudentRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("EnrolledStudent를 저장한다")
    void save() {
        // given
        Session session = sessionRepository.findById(1L);
        NsUser user = NsUserTest.JAVAJIGI;

        EnrolledStudent enrolledStudent = new EnrolledStudent(1L, user.getUserId(), session, LocalDateTime.now());

        // when
        enrolledStudentRepository.save(enrolledStudent);

        // then
        Long count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM enrolled_student WHERE session_id = ? AND user_id = ?",
                Long.class,
                session.getId(),
                user.getId()
        );

        Assertions.assertThat(count).isEqualTo(1L);
    }

}