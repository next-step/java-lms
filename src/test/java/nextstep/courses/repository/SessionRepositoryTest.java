package nextstep.courses.repository;

import nextstep.courses.domain.*;
import nextstep.courses.infrastructure.JdbcImageFileRepository;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({JdbcSessionRepository.class, JdbcImageFileRepository.class})
public class SessionRepositoryTest {

    @Autowired
    JdbcSessionRepository jdbcSessionRepository;

    @Autowired
    JdbcImageFileRepository jdbcImageFileRepository;

    @Test
    void save() {
        ImageFile imageFile = new ImageFile(1024*1024);
        SessionPeriod period = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        SessionStatus sessionStatus = SessionStatus.RECRUITING;
        EnrollmentRule enrollmentRule = new PaidEnrollmentRule(50000, 10);
        Enrollments enrollments = new Enrollments();

        Session session = new Session(imageFile, period, sessionStatus, enrollmentRule, enrollments);

        Long sessionId = jdbcSessionRepository.save(session);

        assertThat(sessionId).isNotNull();
    }

    @Test
    void find() {
        ImageFile imageFile = new ImageFile(1024 * 1024, "png", 300 , 200);
        jdbcImageFileRepository.save(imageFile);

        SessionPeriod period = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        SessionStatus sessionStatus = SessionStatus.RECRUITING;
        EnrollmentRule enrollmentRule = new PaidEnrollmentRule(50000, 10);
        Enrollments enrollments = new Enrollments();

        Session session = new Session(imageFile, period, sessionStatus, enrollmentRule, enrollments);


        Long sessionId = jdbcSessionRepository.save(session);

        Session found = jdbcSessionRepository.findById(sessionId);

        assertThat(found.getSessionStatus()).isEqualTo(session.getSessionStatus());
        assertThat(found.getPeriod()).isEqualTo(session.getPeriod());
    }
}
