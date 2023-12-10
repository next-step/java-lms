package nextstep.courses.domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    void 강의는_강의_커버_이미지_정보와_수강_기간을_가진다() {
        // given

        Image image = new Image();

        Period period = new Period();

        // when
        Session session = new Session(image, period);

        // then
        assertThat(session.getImage()).isEqualTo(image);
        assertThat(session.getPeriod()).isEqualTo(period);
    }
}
