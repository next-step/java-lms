package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class PaySessionTest {

    @Test
    void 유료강의는_최대_수강_인원_제한이_있다() {
        // given
        int maxCountOfStudents = 1;
        PaySession paySession = new PaySession(newImage(), newEnrollment(), maxCountOfStudents,
            1000L);

        // when, then
        paySession.enroll(NsUserTest.JAVAJIGI, new Payment(1000L));
        assertThatThrownBy(() -> paySession.enroll(NsUserTest.SANJIGI, new Payment())).isInstanceOf(
                IllegalArgumentException.class)
            .hasMessageContaining("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.");
    }

    @Test
    void 수강_신청() {
        // given
        Session paySession = new PaySession(newImage(), newEnrollment(), 1, 1000L);

        // when
        paySession.enroll(NsUserTest.JAVAJIGI, new Payment(1000L));

        // then
        assertThat(paySession.getEnrollment().getStudents().size()).isEqualTo(1);
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
