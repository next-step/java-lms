package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTimeTest {

    @Test
    public void validation_이미_시작_테스트() throws Exception {
        // given
        SessionTime sessionTime = new SessionTime();

        // when
        LocalDateTime localDateTime = LocalDateTime.now();
        sessionTime.start(localDateTime);

        // then
        assertThatIllegalStateException().isThrownBy(() -> sessionTime.start(localDateTime));
    }

    @Test
    public void validation_이미_종료_테스트() throws Exception {
        // given
        SessionTime sessionTime = new SessionTime();

        // when
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.now().minusHours(1);
        sessionTime.start(localDateTime2);
        sessionTime.end(localDateTime);

        // then
        assertThatIllegalStateException().isThrownBy(() -> sessionTime.end(localDateTime));
    }

    @Test
    public void validation_시작도_안했는데_종료() throws Exception {
        // given
        SessionTime sessionTime = new SessionTime();

        // when
        LocalDateTime localDateTime = LocalDateTime.now();

        // then
        assertThatIllegalStateException().isThrownBy(() -> sessionTime.end(localDateTime));
    }

    @Test
    public void validation_시작보다_빠른시간() throws Exception {
        // given
        SessionTime sessionTime = new SessionTime();

        // when
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.now().minusHours(1);
        sessionTime.start(localDateTime);

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> sessionTime.end(localDateTime2));
    }

    @Test
    public void 시작_종료_여부() throws Exception {
        // given
        SessionTime sessionTime = new SessionTime();

        // when
        sessionTime.start(LocalDateTime.now());

        // then
        assertThat(sessionTime.isStart()).isTrue();
        assertThat(sessionTime.isEnd()).isFalse();
    }
}
