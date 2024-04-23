package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionFactory;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.session.Period;
import nextstep.courses.entity.ImageEntity;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.fixture.builder.FreeSessionBuilder;
import nextstep.courses.fixture.builder.ImageBuilder;
import nextstep.courses.infrastructure.impl.JdbcImageEntityRepository;
import nextstep.courses.infrastructure.impl.JdbcSessionEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class JdbcSessionEntityRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private ImageRepository imageRepository;


    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionEntityRepository(jdbcTemplate);
        imageRepository = new JdbcImageEntityRepository(jdbcTemplate);
    }

    @Test
    void Session은_저장되어야_한다() {
        Image image = ImageBuilder.anImage()
            .withSize(new ImageSize(1))
            .withType(ImageType.JPG)
            .withWidth(new ImageWidth(300))
            .withHeight(new ImageHeight(200))
            .build();

        Session freeSession = FreeSessionBuilder.anFreeSession()
            .withName("강의이름")
            .withImage(image)
            .withSessionStatus(SessionStatus.RECRUITING)
            .withValidityPeriod(new Period(LocalDateTime.now(), LocalDateTime.MAX))
            .build();

        int saveCount = sessionRepository.save(SessionEntity.from(freeSession, image), 1L);

        assertThat(saveCount).isEqualTo(saveCount);

    }

    @Test
    void Session은_조되어야_한다() {
        Optional<SessionEntity> sessionEntity = sessionRepository.findById(1L);

        assertThat(sessionEntity.isPresent()).isTrue();
        assertThat(sessionEntity.get())
            .extracting("id", "sessionName", "registrationCount", "maxRegistrationCount",
                "tuitionFee", "imageId", "sessionStatus", "startDate", "endDate", "createdAt", "updatedAt")
            .containsExactly(1L, "무료강의1", 1, 2147483647, 0, 1L, "RECRUITING",
                LocalDateTime.parse("2024-01-01T00:00:00"),
                LocalDateTime.parse("2024-01-07T00:00:00"),
                LocalDateTime.parse("2024-01-01T00:00:00"),
                LocalDateTime.parse("2024-01-01T00:00:00"));
    }

    @Test
    void Session의_등록회원수는_변경된값으로_업데이트되어야한다() {
        //given
        Optional<SessionEntity> sessionEntityOptional = sessionRepository.findById(1L);
        assertThat(sessionEntityOptional.isPresent()).isTrue();

        SessionEntity sessionEntity = sessionEntityOptional.get();

        Optional<ImageEntity> imageEntityOptional = imageRepository.findById(sessionEntity.getImageId());
        assertThat(imageEntityOptional.isPresent()).isTrue();
        ImageEntity imageEntity = imageEntityOptional.get();

        Image image = new Image(imageEntity);
        Session session = SessionFactory.createSession(sessionEntity, image, SessionType.FREE);
        session.addRegistrationCount();

        //when
        int updateCount = sessionRepository.updateRegistrationCount(SessionEntity.from(session, image));

        //then
        assertThat(updateCount).isEqualTo(1);
    }

}
