package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.Students;
import nextstep.courses.repository.SessionRepository;
import nextstep.courses.repository.StudentsRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class StudentsRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    private StudentsRepository studentsRepository;
    private SessionRepository sessionRepository;

    @Autowired
    private JdbcOperations jdbcTemplate;

    @BeforeEach
    void setUp() {
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        NsUser nsUser = new NsUser(1L, "javajigi", "test", "자바지기", "javajigi@slipp.net");
        Session session = sessionRepository.findById(3L);
        session.startRecruiting();

        session.register(Payment.ofPaid(1L, 3L, nsUser, 20_000L));
        Long id = studentsRepository.save(nsUser.getId(), 3L);
        assertThat(id).isEqualTo(1L);

        Students students = studentsRepository.findBySessionId(3L);
        assertThat(students.isContains(nsUser)).isTrue();
        LOGGER.debug("Students: {}", students);
    }
}
