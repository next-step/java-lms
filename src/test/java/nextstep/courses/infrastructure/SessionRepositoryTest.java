package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.type.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @DisplayName("강의 저장 테스트")
    @Test
    void 생성테스트() {
        SessionPeriod sessionPeriod = new SessionPeriod("2023-12-01", "2023-12-31");
        Enrolment enrolment = new Enrolment(new ParticipantManager(10), Price.of(false, 10000));
        Session session = new Session("title", sessionPeriod, enrolment, SessionStatus.RECRUIT, null, 1L);
        int result = sessionRepository.save(session);
        assertThat(result).isOne();
    }

    @DisplayName("id로 강의 조회 테스트")
    @Test
    void id로_조회_테스트() {
        SessionPeriod sessionPeriod = new SessionPeriod("2023-12-01", "2023-12-31");
        Enrolment enrolment = new Enrolment(new ParticipantManager(10), Price.of(false, 10000));
        Session session = new Session("title", sessionPeriod, enrolment, SessionStatus.RECRUIT, null, 1L);
        sessionRepository.save(session);
        Session result = sessionRepository.findById(1L);
        assertThat(result.title()).isEqualTo(session.title());
    }

    @DisplayName("Couse id로 강의 조회 테스트")
    @Test
    void course_id로_조회_테스트() {
        SessionPeriod sessionPeriod = new SessionPeriod("2023-12-01", "2023-12-31");
        Enrolment enrolment = new Enrolment(new ParticipantManager(10), Price.of(false, 10000));
        Session session = new Session("title", sessionPeriod, enrolment, SessionStatus.RECRUIT, null, 1L);
        sessionRepository.save(session);
        List<Session> result = sessionRepository.findByCourseId(1L);
        assertThat(result.get(0).title()).isEqualTo(session.title());
    }

}
