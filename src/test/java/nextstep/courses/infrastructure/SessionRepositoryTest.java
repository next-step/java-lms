package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.image.Image;
import nextstep.courses.domain.course.session.image.ImageRepository;
import nextstep.courses.domain.course.session.image.ImageType;
import nextstep.courses.domain.course.session.*;
import nextstep.courses.domain.course.session.image.Images;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);
    private static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private ImageRepository imageRepository;
    private ApplicantsRepository applicantsRepository;

    private Images images;
    private Image image;
    private Payment payment;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Applicants applicants;
    private Duration duration;
    private SessionState sessionState;
    private Session session;
    private Apply apply;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        applicantsRepository = new JdbcApplicantsRepository(jdbcTemplate);

        image = new Image(1024, ImageType.JPG, 300, 200, 1L, LocalDateTime.now());
        List<Image> imageList = new ArrayList<>();
        imageList.add(image);
        images = new Images(imageList);
        payment = new Payment("1", 1L, 1L, 1000L);
        localDate = LocalDate.of(2023, 12, 5);
        localDateTime = LocalDateTime.of(2023, 12, 5, 12, 0);
        applicants = new Applicants();
        duration = new Duration(localDate, localDate);
        sessionState = new SessionState(SessionType.CHARGE, 1000L, 10);
        apply = new Apply(1L, JAVAJIGI.getId(), JAVAJIGI.getId(), localDateTime, localDateTime);
    }

    @Test
    void save_success() {
        session = new Session(images, duration, sessionState, 1L, localDateTime);
        Session savedSession = sessionRepository.save(1L, session);
        //Session savedSession = sessionRepository.findById(1L).orElseThrow(NotFoundException::new);
        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getDuration()).isEqualTo(session.getDuration());
        assertThat(savedSession.getSessionState()).isEqualTo(session.getSessionState());

        //Image savedImage = imageRepository.findById(1L).orElseThrow(NotFoundException::new);

        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    void applySave_success() {
        session = new Session(1L, images, duration, sessionState, new Applicants(),
                SessionStatus.RECRUIT, 1L, localDateTime, localDateTime);
        int count = sessionRepository.saveApply(apply);
        Apply savedApply = sessionRepository.findApplyByIds(JAVAJIGI.getId(), session.getId())
                .orElseThrow(NotFoundException::new);

        assertThat(savedApply.getNsUserId()).isEqualTo(JAVAJIGI.getId());
        assertThat(savedApply.getSessionId()).isEqualTo(session.getId());
    }

    @Test
    void update_success() {
        session = new Session(images, duration, sessionState, 1L, localDateTime);
        Session savedSession = sessionRepository.save(1L, session);

        SessionState updateSessionState = new SessionState(SessionType.CHARGE, 2000L, 30);
        savedSession.setSessionState(updateSessionState);
        sessionRepository.update(savedSession.getId(), savedSession);
        Session updatedSession = sessionRepository.findById(savedSession.getId()).orElseThrow(NotFoundException::new);

        assertThat(updatedSession.getId()).isEqualTo(savedSession.getId());
        assertThat(updatedSession.getSessionState()).isEqualTo(updateSessionState);
        LOGGER.debug("Session: {}", updatedSession);
    }
}
