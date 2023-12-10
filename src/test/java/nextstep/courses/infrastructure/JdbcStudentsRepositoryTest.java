package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class JdbcStudentsRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcStudentsRepository studentsRepository;

    @BeforeEach
    void setUp() {
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
    }

    @Test
    void save() throws PeriodException, CannotRegisterException {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
        Session session = new Session(10L, period, SessionStatus.OPEN, new Students(), new SessionType(PayType.FREE, 1000L, 10), new SessionImage());
        NsUser user = new NsUser(1L, "javajigi", "test", "자바지기", "javajigi@slipp.net");
        session.register(user);
        Students students = studentsRepository.findBySessionId(session.getId());
        int result = studentsRepository.save(session, students);
        assertThat(result).isEqualTo(1);
        Students save = studentsRepository.findBySessionId(session.getId());
        assertThat(save).isEqualTo(new Students(user));
    }
}