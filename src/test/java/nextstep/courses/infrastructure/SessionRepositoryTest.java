package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.image.Image;
import nextstep.courses.domain.course.image.ImageRepository;
import nextstep.courses.domain.course.image.ImageType;
import nextstep.courses.domain.course.session.*;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private ImageRepository imageRepository;
    private ApplicantsRepository applicantsRepository;

    private Image image;
    private Payment payment;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Applicants applicants;
    private Duration duration;
    private SessionState sessionState;
    private Session session;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        applicantsRepository = new JdbcApplicantsRepository(jdbcTemplate);

        image = new Image(1L, 1024, ImageType.JPG, 300, 200, 1L, LocalDateTime.now(), null);
        payment = new Payment("1", 1L, 3L, 1000L);
        localDate = LocalDate.of(2023, 12, 5);
        localDateTime = LocalDateTime.of(2023, 12, 5, 12, 0);
        applicants = new Applicants();
        duration = new Duration(localDate, localDate);
        sessionState = new SessionState(SessionType.FREE, 0L, Integer.MAX_VALUE);
    }

    @Test
    void save_success() {
        imageRepository.save(image);
        Image savedImage = imageRepository.findById(1L).orElseThrow(NotFoundException::new);

        session = new Session(savedImage, duration, sessionState, 1L, localDateTime);
        int count = sessionRepository.save(1L, session);
        Session savedSession = sessionRepository.findById(1L).orElseThrow(NotFoundException::new);
        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getImage()).isEqualTo(session.getImage());
        assertThat(savedSession.getDuration()).isEqualTo(session.getDuration());
        assertThat(savedSession.getSessionState()).isEqualTo(session.getSessionState());
        LOGGER.debug("Session: {}", savedSession);
    }
}
