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
        Session session = new Session(image, period, SessionType.FREE);

        // then
        assertThat(session.getImage()).isEqualTo(image);
        assertThat(session.getPeriod()).isEqualTo(period);
    }

    @Test
    void 강의는_유료강의와_무료강의로_나뉜다() {
        // given
        SessionType payType = SessionType.PAY;
        SessionType freeType = SessionType.FREE;

        // when
        Session paySession = new Session(new Image(), new Period(), payType);
        Session freeSession = new Session(new Image(), new Period(), freeType);

        // then
        assertThat(paySession.getType()).isEqualTo(payType);
        assertThat(freeSession.getType()).isEqualTo(freeType);
    }
}
