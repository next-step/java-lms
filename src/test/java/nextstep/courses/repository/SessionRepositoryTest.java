package nextstep.courses.repository;

import nextstep.courses.domain.*;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(JdbcSessionRepository.class)
public class SessionRepositoryTest {

    @Autowired
    JdbcSessionRepository jdbcSessionRepository;

    @Test
    void save() {

        ImageFile imageFile = new ImageFile(1024*1024);
        SessionPeriod period = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        SessionStatus sessionStatus = SessionStatus.RECRUITING;
        EnrollmentRule enrollmentRule = new PaidEnrollmentRule(50000, 10);
        Enrollments enrollments = new Enrollments();

        Session session = new Session(imageFile, period, sessionStatus, enrollmentRule, enrollments);

        int save = jdbcSessionRepository.save(session);

        assertThat(save).isEqualTo(1);
    }

    @Test
    void find() {
        ImageFile imageFile = new ImageFile(1);
        SessionPeriod period = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        SessionStatus sessionStatus = SessionStatus.RECRUITING;
        EnrollmentRule enrollmentRule = new PaidEnrollmentRule(50000, 10);
        Enrollments enrollments = new Enrollments();

        Session session = new Session(imageFile, period, sessionStatus, enrollmentRule, enrollments);

        jdbcSessionRepository.save(session);

        Session found = jdbcSessionRepository.findById(1L);

        assertThat(found.getSessionStatus()).isEqualTo(session.getSessionStatus());
        assertThat(found.getPeriod()).isEqualTo(session.getPeriod());
    }
}
