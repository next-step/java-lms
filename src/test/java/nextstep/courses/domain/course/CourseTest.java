package nextstep.courses.domain.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.ValidityPeriod;
import nextstep.courses.domain.session.impl.FreeSession;
import nextstep.courses.fixture.builder.FreeSessionBuilder;
import nextstep.courses.fixture.builder.ImageBuilder;
import nextstep.courses.fixture.builder.PaidSessionBuilder;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void 유료강의_등록이_되어야_한다() {
        //given
        Image image = ImageBuilder.anImage()
            .withSize(new ImageSize(1))
            .withType(ImageType.JPG)
            .withWidth(new ImageWidth(300))
            .withHeight(new ImageHeight(200))
            .build();

        FreeSession paidSession = PaidSessionBuilder.anFreeSession()
            .withName("유료강의")
            .withMaxRegistrationCount(5)
            .withTuitionFee(5000)
            .withImage(image)
            .withSessionStatus(SessionStatus.RECRUITING)
            .withValidityPeriod(new ValidityPeriod(LocalDateTime.now(), LocalDateTime.MAX))
            .build();

        Course course = new Course();

        //when
        Session addedSession = course.registerSession(paidSession,
            new Payment("1", 1L, 1L, new Money(0)));

        //then
        assertThat(addedSession.getRegistrationCount()).isEqualTo(1);
    }

    @Test
    void 무료강의_등록이_되어야_한다() {
        //given
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
            .withValidityPeriod(new ValidityPeriod(LocalDateTime.now(), LocalDateTime.MAX))
            .build();

        Course course = new Course();

        //when
        Session addedSession = course.registerSession(freeSession,
            new Payment("1", 1L, 1L, new Money(0)));

        //then
        assertThat(addedSession.getRegistrationCount()).isEqualTo(1);
    }
}
