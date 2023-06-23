package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class SessionTest {

    static Session.SessionBuilder sessionBuilder = Session.builder()
            .imageUrl("http://image.com")
            .period(new Period(LocalDateTime.now().plusHours(1)));

    public static final Session SESSION1 = sessionBuilder.id(1L)
            .sessionType(SessionType.PAY)
            .sessionValidator(new SessionValidator(SessionState.RECRUITING))
            .title("JPA와 함께")
            .build();

    public static final Session SESSION2 = sessionBuilder.id(2L)
            .sessionType(SessionType.FREE)
            .sessionValidator(new SessionValidator(SessionState.CLOSE))
            .title("마이바티스와 함께..")
            .build();

    @Test
    void applyTest_실패_모집중이_아님() {

        System.out.println(SESSION1);
        System.out.println(SESSION2);

        assertThatThrownBy(() -> SESSION2.apply(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 강의는 수강신청중이 아닙니다.");
    }
}