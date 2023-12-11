package nextstep.courses.domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    void 강의는_강의_커버_이미지_정보를_가진다() {
        // given

        Image image = newImage();

        Period period = newPeriod();

        // when
        Session session = new FreeSession(image, newEnrollment());

        // then
        assertThat(session.getImage()).isEqualTo(image);
    }

    @Test
    void 강의는_유료강의와_무료강의로_나뉜다() {
        // given
        FreeSession freeSession = new FreeSession(newImage(), newEnrollment());
        PaySession paySession = new PaySession(newImage(), newEnrollment(), 1, 1000L);

        // when, then
        assertThat(paySession.getType()).isEqualTo(SessionType.PAY);
        assertThat(freeSession.getType()).isEqualTo(SessionType.FREE);
    }

    private Image newImage() {
        ImageSize imageSize = new ImageSize(300, 200);
        ImageType imageType = ImageType.JPG;
        return new Image(imageSize, imageType);
    }

    private Period newPeriod() {
        return new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2024, 12, 1));
    }

    private Enrollment newEnrollment() {
        return new Enrollment(newPeriod(), SessionStatus.RECRUITING);
    }
}
