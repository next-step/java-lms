package nextstep.courses.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Calendar;
import java.util.Date;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    void 강의는_강의_커버_이미지_정보와_수강_기간을_가진다() {
        // given

        Image image = new Image();

        Period period = new Period();

        // when
        Session session = new FreeSession(image, period);

        // then
        assertThat(session.getImage()).isEqualTo(image);
        assertThat(session.getPeriod()).isEqualTo(period);
    }

    @Test
    void 강의는_유료강의와_무료강의로_나뉜다() {
        // given
        FreeSession freeSession = new FreeSession(new Image(), new Period());
        PaySession paySession = new PaySession(new Image(), new Period(), 1);

        // when, then
        assertThat(paySession.getType()).isEqualTo(SessionType.PAY);
        assertThat(freeSession.getType()).isEqualTo(SessionType.FREE);
    }

    @Test
    void 유료강의는_최대_수강_인원_제한이_있다() {
        // given
        int maxCountOfStudents = 1;
        PaySession paySession = new PaySession(new Image(), new Period(), maxCountOfStudents);

        // when, then
        paySession.enroll(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> paySession.enroll(NsUserTest.SANJIGI)).isInstanceOf(
            IllegalArgumentException.class)
            .hasMessageContaining("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.");
    }

    @Test
    void 수강_신청은_모집중일때만_가능하다() {
        // given
        FreeSession freeSession = new FreeSession(new Image(), new Period());

        // when, then
        assertThatThrownBy(() -> freeSession.enroll(NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
    }

    @Test
    void 수강_신청() {
        // given
        FreeSession freeSession = new FreeSession(new Image(), new Period());

        // when
        freeSession.open();
        freeSession.enroll(NsUserTest.JAVAJIGI);

        // then
        assertThat(freeSession.getStudents()).hasSize(1);
    }
}
