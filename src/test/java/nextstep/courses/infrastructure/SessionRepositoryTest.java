package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.engine.Session;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.enrollment.count.FreeEnrollmentCount;
import nextstep.courses.domain.session.enrollment.count.RegistrationCount;
import nextstep.courses.domain.session.enrollment.state.SessionState;
import nextstep.courses.domain.session.feetype.FeeType;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.domain.student.Student;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.factory.SessionFactory;
import nextstep.courses.fixture.builder.EnrollmentBuilder;
import nextstep.courses.fixture.builder.FreeSessionBuilder;
import nextstep.courses.fixture.builder.ImageBuilder;
import nextstep.courses.infrastructure.impl.JdbcImageEntityRepository;
import nextstep.courses.infrastructure.impl.JdbcSessionEntityRepository;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class SessionRepositoryTest {

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
        Enrollment enrollment = EnrollmentBuilder.anEnrollment()
            .withEnrollmentCount(new FreeEnrollmentCount(new RegistrationCount(1)))
            .withSessionState(new SessionState(SessionState.valueOfRecruitmentState("RECRUITING")))
            .withTuitionFee(new Money(0))
            .withFeeType(FeeType.FREE)
            .build();

        Image image = ImageBuilder.anImage()
            .withSize(new ImageSize(1))
            .withType(ImageType.JPG)
            .withWidth(new ImageWidth(300))
            .withHeight(new ImageHeight(200))
            .build();

        Session freeSession = FreeSessionBuilder.anFreeSession()
            .withName("강의이름")
            .withEnrollment(enrollment)
            .withImage(image)
            .withValidityPeriod(new Period(LocalDateTime.now(), LocalDateTime.MAX))
            .build();

        int saveCount = sessionRepository.save(SessionEntity.from(freeSession, 1L), 1L);

        assertThat(saveCount).isEqualTo(saveCount);
    }

    @Test
    void Session은_조되어야_한다() {
        Optional<SessionEntity> sessionEntity = sessionRepository.findById(1L);

        assertThat(sessionEntity.isPresent()).isTrue();
        assertThat(sessionEntity.get())
            .extracting("id", "sessionName", "registrationCount", "maxRegistrationCount",
                "tuitionFee", "imageId", "recruitmentState", "startDate", "endDate", "createdAt", "updatedAt")
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

        Session session = SessionFactory.get(sessionEntityOptional.get());
        Enrollment enrollment = session.getEnrollment();

        Student enroll = enrollment.enroll(
            new NsUser(1L, "namhyeop", "1234", "kimnamhyoep", "nam@gmail.com"),
            new Payment("1", 1L, 1L, new Money(0)));

        Session updateSession = new FreeSession(session, enrollment);

        //when
        int updateCount = sessionRepository.updateRegistrationCount(SessionEntity.from(updateSession, 1L));

        //then
        assertThat(updateCount).isEqualTo(1);
    }

}
