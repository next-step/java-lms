package nextstep.sessions.infrastructure;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.StudentRepository;
import nextstep.sessions.domain.builder.SessionBuilder;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class StudentRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    SessionRepository sessionRepository;

    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
    }

    @Test
    void save() {
        Session tddWithJava = new SessionBuilder()
                .withSessionName("tdd with java")
                .build();
        sessionRepository.save(tddWithJava);

        NsUser nsUser = new NsUser(1L, List.of(tddWithJava), new Payment());

        int count = studentRepository.save(nsUser, tddWithJava);

        assertThat(count).isEqualTo(1);
    }
}
