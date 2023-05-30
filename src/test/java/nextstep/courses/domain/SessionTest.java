package nextstep.courses.domain;

import nextstep.courses.exception.SessionRegistrationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.StudentTest.*;
import static org.assertj.core.api.Assertions.*;

class SessionTest {
    private Session preparingSession;
    private Session recruitingSession;

    @BeforeEach
    void setUp() {
        preparingSession = new Session(1,"session1",new SessionPeriod(SessionPeriodTest.DATE_230601, SessionPeriodTest.DATE_230630),ChargeType.FREE, null, new SessionStudents(10), 1L, LocalDateTime.now(), null);
        recruitingSession = new Session(2,"session2",new SessionPeriod(SessionPeriodTest.DATE_230601, SessionPeriodTest.DATE_230630), ChargeType.FREE, null, SessionStatusType.RECRUITING, new SessionStudents(10), 1L, LocalDateTime.now(), null);
    }

    @Test
    void testRegister_성공() {
        assertThat(recruitingSession.register(SESSION1_STUDENT1)).isTrue();
    }

    @Test
    void testRegister_실패_checkExceedMaximumNumberOfStudents_최대_수강_인원_초과() {
        assertThatThrownBy(() -> {
            for (int i = 0; i < 11; i++) {
                recruitingSession.register(SESSION1_STUDENT1);
            }
        }).isInstanceOf(SessionRegistrationException.class)
                .hasMessageContaining("정원이 초과되었습니다.");
    }

    @Test
    void testRegister_실패_checkRecruiting_모집중_아님() {
        assertThatThrownBy(() -> {
            preparingSession.register(SESSION1_STUDENT1);
        }).isInstanceOf(SessionRegistrationException.class)
                .hasMessageContaining("모집중인 강의가 아닙니다.");
    }

    @Test
    void testCreateSession_성공_coverImageUrl_정상() {
        String coverImageUrl = "https://avatars.githubusercontent.com/u/102819554?s=48&v=4";
        recruitingSession = new Session(2,"session2", new SessionPeriod(SessionPeriodTest.DATE_230601, SessionPeriodTest.DATE_230630), ChargeType.FREE, new ImageUrl(coverImageUrl),SessionStatusType.RECRUITING,new SessionStudents(10), 1L, LocalDateTime.now(), null);
        assertThat(recruitingSession.register(SESSION1_STUDENT1)).isTrue();
    }

    @Test
    void testCreateSession_실패_sessionPeriodIsNull() {
        assertThatThrownBy(() -> {
            new Session(2,"session2", null, ChargeType.FREE, null,SessionStatusType.RECRUITING,new SessionStudents(10), 1L, LocalDateTime.now(), null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("강의 기간을 설정해주세요.");
    }

    @Test
    void testCreateSession_실패_chargeTypeIsNull() {
        assertThatThrownBy(() -> {
            new Session(2,"session2",new SessionPeriod(SessionPeriodTest.DATE_230601, SessionPeriodTest.DATE_230630), null, null, SessionStatusType.RECRUITING, new SessionStudents(10), 1L, LocalDateTime.now(), null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("과금 유형을 선택해주세요.");
    }
}