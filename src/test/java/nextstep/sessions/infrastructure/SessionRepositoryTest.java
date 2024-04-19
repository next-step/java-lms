package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.CountOfStudent;
import nextstep.sessions.domain.ImageRepository;
import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionRegisterDetailsRepository;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import nextstep.sessions.domain.image.Capacity;
import nextstep.sessions.domain.image.Image;
import nextstep.sessions.domain.image.ImageSize;
import nextstep.sessions.domain.image.ImageType;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    SessionRepository sessionRepository;
    ImageRepository imageRepository;
    SessionRegisterDetailsRepository sessionRegisterDetailsRepository;
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
        sessionRegisterDetailsRepository = new JdbcSessionRegisterDetailsRepository(jdbcTemplate, userRepository);
    }

    @DisplayName("세션을 저장하고 조회한다")
    @Test
    void crud() {
        Image image = new Image(1L, new Capacity(100), ImageType.PNG, new ImageSize(300, 200));
        imageRepository.save(image);
        Image imageByFind = imageRepository.findById(1L).orElseThrow();

        SessionRegisterDetails sessionRegisterDetails = new SessionRegisterDetails(1L, new CountOfStudent(20, 40, SessionType.PAID), new Price(100000L), SessionStatus.RECRUITING, List.of(NsUserTest.JAVAJIGI));
        sessionRegisterDetailsRepository.save(sessionRegisterDetails);
        SessionRegisterDetails sessionRegisterDetailsByFind = sessionRegisterDetailsRepository.findById(1L).orElseThrow();

        Session tddWithJava = new Session(
                1L,
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 23, 59),
                "tdd with java",
                imageByFind,
                sessionRegisterDetailsByFind
        );
        sessionRepository.save(tddWithJava);

        Session session = sessionRepository.findById(1L).orElseThrow();
        assertThat(session.getId()).isEqualTo(1L);
    }

}
