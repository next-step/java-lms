package nextstep.courses.infrastructure;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.AttendeeRepository;
import nextstep.courses.domain.image.*;
import nextstep.courses.domain.session.*;
import nextstep.qna.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static nextstep.courses.domain.attendee.Approval.*;
import static nextstep.courses.domain.image.ImageFormat.*;
import static nextstep.courses.domain.session.Recruitment.*;
import static nextstep.courses.domain.session.SessionStatus.*;
import static nextstep.courses.domain.session.SessionType.*;
import static org.assertj.core.api.Assertions.*;

@JdbcTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private AttendeeRepository attendeeRepository;

    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        attendeeRepository = new JdbcAttendeeRepository(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, attendeeRepository, imageRepository);
    }

    @DisplayName("무료 강의를 조회한다.")
    @Test
    void find_free_session_by_id() {
        Attendee attendee1 = new Attendee(1L, 1L, NOT_APPROVED);
        Attendee attendee2 = new Attendee(2L, 1L, NOT_APPROVED);
        attendeeRepository.save(attendee1);
        attendeeRepository.save(attendee2);
        SessionInformation information = getSessionInformation();
        EnrollmentSession expected = new EnrollmentSession(1L,
                                                           information,
                                                           EnrollmentFactory.create(FREE,
                                                                                    List.of(attendee1, attendee2),
                                                                                    0L,
                                                                                    2));
        EnrollmentSession actual = sessionRepository.findBySessionId(1L)
                                                         .orElseThrow(NotFoundException::new);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("유료 강의를 조회한다.")
    @Test
    void find_paid_session_by_id() {
        Attendee attendee1 = new Attendee(1L, 2L, NOT_APPROVED);
        Attendee attendee2 = new Attendee(2L, 2L, NOT_APPROVED);
        attendeeRepository.save(attendee1);
        attendeeRepository.save(attendee2);
        SessionInformation information = getSessionInformation();
        EnrollmentSession expected = new EnrollmentSession(2L,
                                                           information,
                                                           EnrollmentFactory.create(PAID,
                                                                                    List.of(attendee1, attendee2),
                                                                                    1000L,
                                                                                    2));
        EnrollmentSession actual = sessionRepository.findBySessionId(2L)
                                                    .orElseThrow(NotFoundException::new);

        assertThat(actual).isEqualTo(expected);
    }

    private static SessionInformation getSessionInformation() {
        ImageInformation imageInformation= new ImageInformation(new ImageSize(300.0, 200.0),
                                                                100,
                                                                JPG);
        return new SessionInformation(PREPARING,
                                      new Period(LocalDate.parse("2023-12-01"),
                                                 LocalDate.parse("2023-12-02")),
                                      new Images(new Image(1L, imageInformation)),
                                      RECRUITING);
    }
}