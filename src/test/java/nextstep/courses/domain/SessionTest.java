package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class SessionTest {

    private final Period period = new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25));
    private final Thumbnail thumbnail = new Thumbnail(1, "thumbnail.png", 1024L * 1024L, 300, 200);
    private SessionType sessionType = SessionType.determineSessionType(true, 1L, 100, 100);
    private SessionStatus sessionStatus = SessionStatus.RECRUITING;

    @Test
    public void isRecruiting_모집중일때_TRUE_반환테스트() {
        Session recruitingSession = new Session(1L, "session", period, thumbnail,
                sessionType, sessionStatus);

        assertThat(recruitingSession.isRecruiting()).isTrue();
    }

    @Test
    public void isRecruiting_강연종료시_FALSE_반환테스트() {
        sessionStatus = SessionStatus.FINISHED;

        Session finishedSession = new Session(1L, "session", period, thumbnail,
                sessionType, sessionStatus);

        assertThat(finishedSession.isRecruiting()).isFalse();
    }

    @Test
    public void isRecruiting_강연준비중일시_False_반환테스트() {
        sessionStatus = SessionStatus.PREPARING;

        Session preparingSession = new Session(1L, "session", period, thumbnail,
                sessionType, sessionStatus);

        assertThat(preparingSession.isRecruiting()).isFalse();
    }

    @Test
    public void checkSessionFeeEquality_수강료_일치_시_True_반환테스트() {
        Session session = new Session(1L, "session", period, thumbnail,
                sessionType, sessionStatus);
        assertThat(session.checkSessionFeeEquality(100)).isTrue();
    }

    @Test
    public void checkSessionFeeEquality_수강료_불일치_시_False_반환테스트() {
        Session session = new Session(1L, "session", period, thumbnail,
                sessionType, sessionStatus);
        assertThat(session.checkSessionFeeEquality(3000)).isFalse();
    }

    @Test
    public void checkSessionFeeEquality_무료강의_수강료_일치여부_관계없이_True_반환테스트() {
        sessionType = SessionType.determineSessionType(false, 1L, 2, 10000);

        Session session = new Session(1L, "session", period, thumbnail,
                sessionType, sessionStatus);
        assertThat(session.checkSessionFeeEquality(3000)).isTrue();
    }
}
