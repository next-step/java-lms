package nextstep.courses.domain;

import nextstep.courses.domain.image.*;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.SessionInformation;
import nextstep.courses.exception.CanNotApplyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.courses.domain.image.ImageFormat.*;
import static nextstep.courses.domain.session.SessionStatus.*;
import static org.assertj.core.api.Assertions.*;

class SessionInformationTest {

    @DisplayName("모집 중이 아닐 때 수강신청할 경우 예외가 발생한다.")
    @Test
    void should_be_being_recruited_when_applying_for_session() {
        Period period = new Period(LocalDate.now(),
                                   LocalDate.now().plusDays(1));
        ImageInformation imageInformation = new ImageInformation(new ImageSize(300.0, 200.0),
                                                                 100,
                                                                 JPG);
        Image image = new Image(1L, imageInformation);
        SessionInformation information = new SessionInformation(PREPARING, period, new Images(image));

        assertThatThrownBy(information::validateApply)
                .isInstanceOf(CanNotApplyException.class);
    }
}